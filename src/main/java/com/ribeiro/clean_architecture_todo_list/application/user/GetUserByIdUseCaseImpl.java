package com.ribeiro.clean_architecture_todo_list.application.user;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.exceptions.ResourceNotFoundException;
import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.user.GetUserByIdUseCase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {
		
	private final UserRepository userRepository; 

	@Override
	public User execute(Long id) {
		return this.userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")); 
	}

}