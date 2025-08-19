package com.ribeiro.clean_architecture_todo_list.adapters.mappers;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo.UpdateTodoRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo.CreateTodoRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.todo.TodoResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TodoDTOMapper {
	public Todo toDomain(CreateTodoRequestDTO todo, User user) {
		return new Todo(todo.title(), todo.description(), user);		
	}
	
	public Todo toDomain(UpdateTodoRequestDTO todo) {
		return new Todo(todo.title(), todo.description()); 
	}

	public TodoResponseDTO toResponse(Todo todo) {
		return new TodoResponseDTO(todo.getId(), todo.getTitle(), todo.getDescription()); 
	}


	public List<TodoResponseDTO> toResponseList(List<Todo> todoList) {
		return todoList
			.stream()
			.map(this::toResponse)
			.collect(Collectors.toList()); 
	}

}