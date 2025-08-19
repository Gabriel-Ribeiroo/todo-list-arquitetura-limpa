package com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record TodoResponseDTO(
	
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	Long id, 

	@Schema(example = "Estudar programação", requiredMode = RequiredMode.REQUIRED)
	String title, 

	@Schema(example = "Estou estudando programação para conseguir um emprego", requiredMode = RequiredMode.REQUIRED)
	String description

) {}