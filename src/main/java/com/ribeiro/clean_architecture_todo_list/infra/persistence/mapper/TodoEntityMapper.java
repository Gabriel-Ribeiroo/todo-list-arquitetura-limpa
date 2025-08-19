package com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.entity.TodoEntity;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TodoEntityMapper {

	private final UserEntityMapper userEntityMapper; 

	public TodoEntity toEntity(Todo domainTodo) {
		var user = userEntityMapper.toEntity(domainTodo.getUser()); 

		if (domainTodo.getId() == null) {
			return new TodoEntity(domainTodo.getTitle(), domainTodo.getDescription(), user); 
		}

		return new TodoEntity(domainTodo.getId(), domainTodo.getTitle(), domainTodo.getDescription(), user); 
		
	}

	public Todo toDomain(TodoEntity entityTodo) {
		var user = userEntityMapper.toDomain(entityTodo.getUser()); 

		return new Todo(entityTodo.getId(), entityTodo.getTitle(), entityTodo.getDescription(), user); 
	}

	public List<Todo> toDomainList(List<TodoEntity> entityList) {
		List<Todo> domainList = entityList
			.stream()
			.map(this::toDomain)
			.collect(Collectors.toList());

		return domainList; 
	}
}
