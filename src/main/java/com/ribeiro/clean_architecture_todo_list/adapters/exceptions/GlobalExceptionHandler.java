package com.ribeiro.clean_architecture_todo_list.adapters.exceptions;

import com.ribeiro.clean_architecture_todo_list.domain.exceptions.BadCredentialsException;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException exception) {
		var response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage()); 

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); 
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse>  badCredentials(BadCredentialsException exception) {
		var response = new ExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	}


	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
		var response = new ExceptionResponse(
			HttpStatus.BAD_REQUEST, 
			String.format("O valor \"%s\" não é válido para o parâmetro %s", exception.getValue(), exception.getName())
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
	}	


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> methodArgumentNotValid(MethodArgumentNotValidException excecption) {
		var response = new ExceptionResponse(
			HttpStatus.BAD_REQUEST, 
			excecption.getBindingResult().getFieldErrors().get(0).getDefaultMessage()
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	}
	
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ExceptionResponse> internalServerError(Exception exception) {
		var response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno."); 

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
	}

}