package com.ribeiro.clean_architecture_todo_list.adapters.exceptions;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

	@Schema(
		example = "STATUS_HTTP_DE_ERRO", 
		description = "O status HTTP do erro, por exemplo: BAD_REQUEST, INTERNAL_SERVER_ERROR, NOT_FOUND",
		requiredMode = RequiredMode.REQUIRED
	)
	private final HttpStatus httpStatus; 

	@Schema(
		example = "Algo deu errado! Tente novamente.", 
		description = "Uma mensagem que descreve o erro",
		requiredMode = RequiredMode.REQUIRED
	)
	private final String message;
	
}