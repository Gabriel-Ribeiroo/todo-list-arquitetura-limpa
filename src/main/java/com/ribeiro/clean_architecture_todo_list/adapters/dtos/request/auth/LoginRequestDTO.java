package com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

	@NotBlank(message ="O e-mail é obrigatório")
	@Email(message = "O e-mail é obrigatório")
	@Schema(example = "joao123silva@gmail.com", requiredMode = RequiredMode.REQUIRED)
	String email, 

	@NotBlank(message = "A senha é obrigatória")
	@Schema(example = "Programação123@Joao#1", requiredMode = RequiredMode.REQUIRED)
	String password

) {}