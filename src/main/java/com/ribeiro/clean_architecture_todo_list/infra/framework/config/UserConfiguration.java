package com.ribeiro.clean_architecture_todo_list.infra.framework.config;

import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.user.GetUserByIdUseCase;

import com.ribeiro.clean_architecture_todo_list.application.user.GetUserByIdUseCaseImpl;

import com.ribeiro.clean_architecture_todo_list.adapters.mappers.UserDTOMapper;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.gateway.UserRepositoryGateway;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper.UserEntityMapper;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.repository.UserJpaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

	@Bean 
	GetUserByIdUseCase getUserByIdUseCase(UserRepository userRepository) {
		return new GetUserByIdUseCaseImpl(userRepository); 
	}

	@Bean
	UserRepository userRepository(UserJpaRepository userJpaRepository, UserEntityMapper userEntityMapper) {
		return new UserRepositoryGateway(userJpaRepository, userEntityMapper);
	}

	@Bean 
	UserEntityMapper userEntityMapper() {
		return new UserEntityMapper(); 
	}

	@Bean
	UserDTOMapper userDTOMapper() {
		return new UserDTOMapper(); 
	}

}