package com.ribeiro.clean_architecture_todo_list.domain.usecase.todo;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;

public interface CreateTodoUseCase {
	Todo execute(Todo todo); 
}