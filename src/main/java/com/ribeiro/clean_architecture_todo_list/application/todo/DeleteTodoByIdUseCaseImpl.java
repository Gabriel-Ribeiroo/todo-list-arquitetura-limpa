package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.DeleteTodoByIdUseCase;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DeleteTodoByIdUseCaseImpl implements DeleteTodoByIdUseCase {
	
	private final TodoRepository todoRepositoryGateway;

	@Override
	public void execute(Long id) {
		this.todoRepositoryGateway.deleteById(id);	
	}
}
