package com.ribeiro.clean_architecture_todo_list.application.auth;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceAlreadyExistsException;
import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;
import com.ribeiro.clean_architecture_todo_list.domain.security.PasswordEncryptor;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterUseCaseImpl implements RegisterUseCase {
	
	private final UserRepository userRepository;
	private final PasswordEncryptor passwordEncryptor;  

	@Override
	public User execute(User user) {
		if (this.userRepository.findUserByEmail(user.getEmail()).isPresent()) {
			throw new ResourceAlreadyExistsException("E-mail já está em uso"); 
		}

		String hashedPassword = this.passwordEncryptor.hash(user.getPassword());

		return this.userRepository.save(new User(user.getUsername(), user.getEmail(), hashedPassword));  
	}
	
}