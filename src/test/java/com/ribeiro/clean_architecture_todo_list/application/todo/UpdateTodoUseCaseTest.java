package com.ribeiro.clean_architecture_todo_list.application.todo;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.CreateTodoUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.UpdateTodoUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UpdateTodoUseCaseTest {
	
	@Autowired
	private CreateTodoUseCase createTodoUseCase; 

	@Autowired 
	private UpdateTodoUseCase updateTodoUseCase; 

	@Autowired 
	private RegisterUseCase registerUseCase;

	@Test
	@DisplayName("should update user successfully")
	public void testTodoIsUpdatedSuccessfully() {
		var user = this.registerUseCase.execute(
			new User("John Doe", "johndoe123@gmail.com", "John123LoreM")
		);
		
		var todo = this.createTodoUseCase.execute(
			new Todo("Lorem ipsum", "Lorem ipsum dolor sit amet dolor lorem amet sit", user)
		);

		var updatedTodo = this.updateTodoUseCase.execute(
			todo, 
			new Todo("Ipsum lorem", "sit amet lorem dolor amet sit dolor ipsum lorem")
		);

		assertEquals(updatedTodo.getTitle(), "Ipsum lorem");
		assertEquals(updatedTodo.getDescription(), "sit amet lorem dolor amet sit dolor ipsum lorem");
	}

}