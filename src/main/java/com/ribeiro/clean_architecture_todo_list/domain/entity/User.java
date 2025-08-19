package com.ribeiro.clean_architecture_todo_list.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
	
	private Long id; 
	private String username; 
	private String email; 
	private String password; 

	public User(String username, String email, String password) {
		this.username = username; 
		this.email = email; 
		this.password = password; 
	}

}