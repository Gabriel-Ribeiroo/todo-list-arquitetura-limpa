package com.ribeiro.clean_architecture_todo_list.infra.security.gateway;

import com.ribeiro.clean_architecture_todo_list.domain.security.PasswordEncryptor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordEncryptorGateway implements PasswordEncryptor {
	
	private final BCryptPasswordEncoder encoder; 
	
	@Override
	public String hash(String password) {
		return this.encoder.encode(password); 
	}

	@Override
	public boolean matches(String password, String hashedPassword) {
		return this.encoder.matches(password, hashedPassword);
	}

}