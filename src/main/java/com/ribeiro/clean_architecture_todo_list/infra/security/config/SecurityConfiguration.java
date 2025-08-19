package com.ribeiro.clean_architecture_todo_list.infra.security.config; 

import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;
import com.ribeiro.clean_architecture_todo_list.domain.security.AccessTokenGenerator;
import com.ribeiro.clean_architecture_todo_list.domain.security.PasswordEncryptor;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.LoginUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;

import com.ribeiro.clean_architecture_todo_list.application.auth.LoginUseCaseImpl;
import com.ribeiro.clean_architecture_todo_list.application.auth.RegisterUseCaseImpl;

import com.ribeiro.clean_architecture_todo_list.infra.security.gateway.AccessTokenGeneratorGateway;
import com.ribeiro.clean_architecture_todo_list.infra.security.gateway.PasswordEncryptorGateway;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	private static final String[] SWAGGER_ROUTES = {
		"/swagger-ui/**",
		"/v3/api-docs/**",
		"swagger-resources/**"
	}; 

	@Value("${jwt.public.key}")
	private RSAPublicKey publicKey;

	@Value("${jwt.private.key}")
	private RSAPrivateKey privateKey;


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.authorizeHttpRequests(auth -> 
				auth
				.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
				.requestMatchers(SWAGGER_ROUTES).permitAll()
				.anyRequest().authenticated()	
			)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(AbstractHttpConfigurer::disable)
			.oauth2ResourceServer(oauth2 -> oauth2
				.jwt(Customizer.withDefaults())
				.bearerTokenResolver(new CustomJwtResolver())
			)
			.build(); 
	}

	@Bean 
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
	}

	@Bean 
	JwtEncoder jwtEncoder() {
		var jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build(); 	
		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk)); 

		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}

	@Bean 
	PasswordEncryptor passwordEncryptor(BCryptPasswordEncoder encoder) {
		return new PasswordEncryptorGateway(encoder); 
	}

	@Bean 
	AccessTokenGenerator accessTokenGenerator(JwtEncoder encoder) {
		return new AccessTokenGeneratorGateway(encoder); 
	}

	@Bean
	RegisterUseCase registerUserUseCase(UserRepository userRepositoryGateway, PasswordEncryptor passwordEncryptorGateway) {
		return new RegisterUseCaseImpl(userRepositoryGateway, passwordEncryptorGateway);
	}

	@Bean
	LoginUseCase loginUserUseCase(UserRepository userRepository, PasswordEncryptor passwordEncryptor, AccessTokenGenerator accessTokenGenerator) {
		return new LoginUseCaseImpl(userRepository, passwordEncryptor, accessTokenGenerator);
	}

}