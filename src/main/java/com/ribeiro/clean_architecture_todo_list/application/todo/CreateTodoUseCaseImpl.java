package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.CreateTodoUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTodoUseCaseImpl implements CreateTodoUseCase {
	
	private final TodoRepository todoRepositoryGateway; 

	@Override
	public Todo execute(Todo todo) {
		return this.todoRepositoryGateway.save(todo);
	}

}
