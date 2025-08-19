package com.ribeiro.clean_architecture_todo_list.infra.security.gateway;

import com.ribeiro.clean_architecture_todo_list.domain.security.AccessTokenGenerator;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccessTokenGeneratorGateway implements AccessTokenGenerator {
	
	private final JwtEncoder jwtEncoder; 

	@Override
	public String generate(Long userId) {
		var now = Instant.now(); 
		
		var claims = JwtClaimsSet.builder()
			.issuedAt(now)
			.expiresAt(now.plusSeconds(3600))
			.subject(userId.toString())
			.issuer("clean_arch_back_end")
			.build(); 

		var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); 

		return token; 
	}

}