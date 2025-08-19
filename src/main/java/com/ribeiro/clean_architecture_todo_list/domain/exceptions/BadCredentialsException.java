package com.ribeiro.clean_architecture_todo_list.domain.exceptions;

public class BadCredentialsException extends RuntimeException {

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException() {
		super("Credenciais inválidas"); 
	}

}