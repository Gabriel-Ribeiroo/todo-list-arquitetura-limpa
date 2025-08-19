package com.ribeiro.clean_architecture_todo_list.application.auth;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceAlreadyExistsException;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RegisterUserUseCaseTest {

	@Autowired
	private RegisterUseCase registerUserUseCase; 

	@Test
	@DisplayName("Should successfully register user if the email is not in use")
	public void testUserIsSuccessfullyCreated() {
		var name = "John Doe";
		var email = "johndoe123@gmail.com";
		var password = "John123LoreM"; 
		
		var user = registerUserUseCase.execute(new User(name, email, password)); 

		assertEquals(name, user.getUsername(), "Username does not match"); 
		assertEquals(email, user.getEmail(), "E-mail does not match");
		assertNotEquals(password, user.getPassword(), "Password should be hashed");
	}

	@Test 
	@DisplayName("Should throw [UserAlreadyExistsException] if e-mail is already in use") 
	public void testUserAlreadyExistsException() {
		var firstUserName = "John Doe";
		var firstUserEmail = "johndoe123@gmail.com";
		var firstUserPassword = "John123LoreM";

		registerUserUseCase.execute(new User(firstUserName, firstUserEmail, firstUserPassword));

		var secondUserName = "Jane Doe"; 
		var secondUserEmail = "johndoe123@gmail.com"; 
		var secondUserPassword = "Jane123LoreM"; 

		assertThrows(ResourceAlreadyExistsException.class, () -> {
			registerUserUseCase.execute(new User(secondUserName, secondUserEmail, secondUserPassword));
		}); 
	}

}