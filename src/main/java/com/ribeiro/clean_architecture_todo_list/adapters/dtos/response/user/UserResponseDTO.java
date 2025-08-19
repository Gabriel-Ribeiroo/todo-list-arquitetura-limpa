package com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record UserResponseDTO(
	
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	Long id, 
	
	@Schema(example = "João da Silva", requiredMode = RequiredMode.REQUIRED)
	String username, 

	@Schema(example = "joao123silva@gmail.com", requiredMode = RequiredMode.REQUIRED)
	String email

) {}