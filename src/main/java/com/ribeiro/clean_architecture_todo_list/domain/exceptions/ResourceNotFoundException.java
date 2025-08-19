package com.ribeiro.clean_architecture_todo_list.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
		super(message); 
	}

	public ResourceNotFoundException() {
		super("Recurso não encontrado");
	}



}
