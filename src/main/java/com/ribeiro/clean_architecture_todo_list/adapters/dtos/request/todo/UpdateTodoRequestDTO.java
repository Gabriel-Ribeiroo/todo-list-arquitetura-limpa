package com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import jakarta.validation.constraints.Size;

public record UpdateTodoRequestDTO(
	
	@Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
	@Schema(example = "Novo título para minha tarefa!", requiredMode = RequiredMode.NOT_REQUIRED, maxLength = 100)
	String title, 

	@Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
	@Schema(example = "Nova descrição para minha tarefa!", requiredMode = RequiredMode.NOT_REQUIRED, maxLength = 1000)
	String description

) {}