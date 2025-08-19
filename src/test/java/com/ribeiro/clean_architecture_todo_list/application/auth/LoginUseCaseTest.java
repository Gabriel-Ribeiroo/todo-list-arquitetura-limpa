package com.ribeiro.clean_architecture_todo_list.application.auth;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.BadCredentialsException;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.LoginUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class LoginUseCaseTest {
	
	private static final String EMAIL = "lorem@ipsum.com";
	private static final String PASSWORD = "X!13@.z3493A8g8IYT3EGhd@FDFaf@123";

	@Autowired
	private LoginUseCase loginUserUseCase; 

	@Autowired 
	private RegisterUseCase registerUseCase;

	@BeforeEach
	void setUp() {
		this.registerUseCase.execute(new User("John Doe", EMAIL, PASSWORD)); 
	}

	@Test
	@DisplayName("Should throw [UserBadCredentialsException] if user is not found")
	public void testUserNotFoundException() {
		assertThrows(BadCredentialsException.class, () -> {
			loginUserUseCase.execute("wrongEmail@lorem.com", "PASSWORD"); 
		});
	}

	@Test 
	@DisplayName("Should throw [UserBadCredentialsException] if password does not match")
	public void testIfPasswordDoesNotMatch() {
		assertThrows(BadCredentialsException.class, () -> {
			loginUserUseCase.execute(EMAIL, "wrongPassword");
		});
	}

	@Test
	@DisplayName("Should login user successfully") 
	public void testShouldLoginUserSuccessfully() {
		var accessToken = this.loginUserUseCase.execute(EMAIL, PASSWORD); 

		assertNotNull(accessToken);
	}
	
}