package com.ribeiro.clean_architecture_todo_list.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title; 
	private String description;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user; 
	
	public TodoEntity(String title, String description, UserEntity user) {
		this.title = title; 
		this.description = description; 
		this.user = user; 
	}

}