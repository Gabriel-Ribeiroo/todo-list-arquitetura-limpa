package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceNotFoundException;
import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodoByIdUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetTodoByIdUseCaseImpl implements GetTodoByIdUseCase {
	
	private final TodoRepository todoRepositoryGateway; 

	@Override
	public Todo execute(Long id, Long userId) {
		var todo = this.todoRepositoryGateway
			.getById(id, userId)
			.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada")); 
			
		return todo; 
	}

}