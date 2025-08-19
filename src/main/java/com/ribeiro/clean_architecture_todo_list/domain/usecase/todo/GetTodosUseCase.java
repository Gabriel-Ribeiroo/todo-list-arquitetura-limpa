package com.ribeiro.clean_architecture_todo_list.domain.usecase.todo;

import java.util.List;
import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo; 

public interface GetTodosUseCase {
	List<Todo> execute(Long userId); 
}
