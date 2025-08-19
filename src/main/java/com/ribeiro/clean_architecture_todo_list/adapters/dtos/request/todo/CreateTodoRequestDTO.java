package com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTodoRequestDTO(

	@NotBlank(message = "O título é obrigatório")
	@Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
	@Schema(example = "Estudar programação", requiredMode = RequiredMode.REQUIRED, minLength = 1, maxLength = 100)
	String title,
	
	@Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
	@Schema(
		example = "Estudar: HTML, CSS, JavaScript, React, Next.js, Java, Spring Boot...", 
		requiredMode = RequiredMode.NOT_REQUIRED,
		maxLength = 1000
	)
	String description
	
) {}