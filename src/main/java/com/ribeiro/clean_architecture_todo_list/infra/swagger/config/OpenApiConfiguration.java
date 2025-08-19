package com.ribeiro.clean_architecture_todo_list.infra.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes("jwt", this.generateJWTSchema())
			)
			.info(new Info()
				.title("Lista de tarefas")
				.description("API desenvolvida com o íntuito de estudar arquitetura limpa")
				.version("v1")
				.license(new License()
					.name("MIT")
					.url("https://opensource.org/licenses/MIT")	
				)
			);
	}

	public SecurityScheme generateJWTSchema() {
		return new SecurityScheme()
			.name("accessToken")
			.type(SecurityScheme.Type.APIKEY)
			.in(SecurityScheme.In.COOKIE); 
	} 

}