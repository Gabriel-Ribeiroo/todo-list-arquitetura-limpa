package com.ribeiro.clean_architecture_todo_list.domain.security;

public interface PasswordEncryptor {
	String hash(String password); 
	boolean matches(String password, String hashedPassword); 
}