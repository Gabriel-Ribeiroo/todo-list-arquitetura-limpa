package com.ribeiro.clean_architecture_todo_list.domain.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

	public ResourceAlreadyExistsException() {
		super("Recurso já existe");
	}

}