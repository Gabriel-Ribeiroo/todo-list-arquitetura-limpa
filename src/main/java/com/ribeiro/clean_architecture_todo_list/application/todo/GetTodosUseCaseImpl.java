package com.ribeiro.clean_architecture_todo_list.application.todo;

import java.util.List;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodosUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetTodosUseCaseImpl implements GetTodosUseCase {
	private final TodoRepository todoRepositoryGateway; 

	@Override
	public List<Todo> execute(Long userId) {
		return this.todoRepositoryGateway.getAll(userId); 
	}

}