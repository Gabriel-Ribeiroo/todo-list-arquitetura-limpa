package com.ribeiro.clean_architecture_todo_list.application.user;

import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceNotFoundException;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.user.GetUserByIdUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SpringBootTest
@ActiveProfiles("test")
public class GetUserByIdUseCaseTest {
	
	@Autowired  
	private GetUserByIdUseCase getUserByIdUseCase;  

	@Test
	@DisplayName("Should throw [ResourceNotFoundException] if todo is not found")
	public void testTodoNotFoundException() {
		var id = 1L; 	

		assertThrows(ResourceNotFoundException.class, () -> {
			getUserByIdUseCase.execute(id); 
		});
	}

}
