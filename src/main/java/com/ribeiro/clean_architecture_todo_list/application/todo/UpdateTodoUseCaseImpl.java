package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.UpdateTodoUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTodoUseCaseImpl implements UpdateTodoUseCase {
	
	private final TodoRepository todoRepository; 

	@Override
	public Todo execute(Todo todo, Todo newData) {
		System.out.println("Todo: " + todo.getId());
		
		if (newData.getTitle() != null) {
			todo.setTitle(newData.getTitle()); 
		}

		if (newData.getDescription() != null) {
			todo.setDescription(newData.getDescription());
		}
		
		return this.todoRepository.save(todo); 
	}

}
