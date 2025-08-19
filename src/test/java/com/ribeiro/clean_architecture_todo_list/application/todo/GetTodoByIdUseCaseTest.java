package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceNotFoundException;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodoByIdUseCase;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class GetTodoByIdUseCaseTest {

	@Autowired
	private GetTodoByIdUseCase getTodoByIdUseCase; 

	@Test
	@DisplayName("Should throw [ResourceNotFoundException] if todo is not found")
	public void testTodoNotFoundException() {
		var todoId = 1L; 	
		var userId = 1L; 

		assertThrows(ResourceNotFoundException.class, () -> {
			getTodoByIdUseCase.execute(todoId, userId);
		});
	}

}