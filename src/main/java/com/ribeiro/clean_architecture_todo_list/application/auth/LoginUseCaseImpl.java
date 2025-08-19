package com.ribeiro.clean_architecture_todo_list.application.auth;

import com.ribeiro.clean_architecture_todo_list.domain.exceptions.BadCredentialsException;
import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;
import com.ribeiro.clean_architecture_todo_list.domain.security.AccessTokenGenerator;
import com.ribeiro.clean_architecture_todo_list.domain.security.PasswordEncryptor;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.LoginUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

	private final UserRepository userRepository;
	private final PasswordEncryptor passwordEncryptor; 
	private final AccessTokenGenerator accessTokenGenerator; 

	@Override
	public String execute(String email, String password) {
		var user = this.userRepository.findUserByEmail(email)
			.orElseThrow(() -> new BadCredentialsException("E-mail ou senha inválidos"));

		if (!passwordEncryptor.matches(password, user.getPassword())) {
			throw new BadCredentialsException("E-mail ou senha inválidos"); 
		}		

		return accessTokenGenerator.generate(user.getId()); 
	}

}
