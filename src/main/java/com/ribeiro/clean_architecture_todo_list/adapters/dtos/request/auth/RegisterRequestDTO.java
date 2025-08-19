package com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
	
	@NotBlank(message = "O nome de usuário é obrigatório")
	@Size(min = 3, max = 30, message = "O nome de usuário deve ter entre 3 e 30 caracteres")
	@Schema(example = "João da Silva", requiredMode = RequiredMode.REQUIRED, minLength = 3, maxLength = 30)
	String username, 

	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message =  "E-mail inválido")
	@Size(min = 6, max = 254, message = "O e-mail deve ter entre 6 e 254 caracteres")
	@Schema(example = "joao123silva@gmail.com", requiredMode = RequiredMode.REQUIRED)
	String email, 

	@NotBlank(message = "Senha inválida")
	@Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
	@Schema(example = "Programação123@Joao#1", requiredMode = RequiredMode.REQUIRED, minLength = 7,maxLength = 100)
	String password

) {} 