package com.ribeiro.clean_architecture_todo_list.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todo {

	private Long id; 
	private String title; 
	private String description; 
	private User user;

	public Todo(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Todo(String title, String description, User user) {
		this.title = title; 
		this.description = description; 
		this.user = user; 
	}

	public void setTitle(String title) {
		this.title = title; 
	} 

	public void setDescription(String description) {
		this.description = description; 
	}

}