package com.ribeiro.clean_architecture_todo_list.infra.framework.config;

import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.CreateTodoUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.DeleteTodoByIdUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodoByIdUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodosUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.UpdateTodoUseCase;

import com.ribeiro.clean_architecture_todo_list.application.todo.CreateTodoUseCaseImpl;
import com.ribeiro.clean_architecture_todo_list.application.todo.DeleteTodoByIdUseCaseImpl;
import com.ribeiro.clean_architecture_todo_list.application.todo.GetTodoByIdUseCaseImpl;
import com.ribeiro.clean_architecture_todo_list.application.todo.GetTodosUseCaseImpl;
import com.ribeiro.clean_architecture_todo_list.application.todo.UpdateTodoUseCaseImpl;

import com.ribeiro.clean_architecture_todo_list.adapters.mappers.TodoDTOMapper;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.gateway.TodoRepositoryGateway;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper.TodoEntityMapper;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper.UserEntityMapper;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.repository.TodoJpaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfiguration {

	@Bean 
	GetTodosUseCase getTodosUseCase(TodoRepository todoRepositoryGateway) {
		return new GetTodosUseCaseImpl(todoRepositoryGateway);
	}
	
	@Bean 
	GetTodoByIdUseCase getTodoByIdUseCase(TodoRepository todoRepositoryGateway) {
		return new GetTodoByIdUseCaseImpl(todoRepositoryGateway); 
	} 

	@Bean 
	CreateTodoUseCase createTodoUseCase(TodoRepository todoRepositoryGateway) {
		return new CreateTodoUseCaseImpl(todoRepositoryGateway); 
	}

	@Bean 
	DeleteTodoByIdUseCase deleteByIdTodoUseCase(TodoRepository todoRepositoryGateway) {
		return new DeleteTodoByIdUseCaseImpl(todoRepositoryGateway); 
	}

	@Bean
	UpdateTodoUseCase upadteTodoUseCase(TodoRepository todoRepositoryGateway) {
		return new UpdateTodoUseCaseImpl(todoRepositoryGateway); 
	}

	@Bean 
	TodoRepository todoGateway(TodoJpaRepository todoJpaRepository, TodoEntityMapper todoEntityMapper) {
		return new TodoRepositoryGateway(todoJpaRepository, todoEntityMapper);	
	} 

	@Bean
	TodoDTOMapper todoDTOMapper() {
		return new TodoDTOMapper(); 
	}

	@Bean 
	TodoEntityMapper todoEntityMapper(UserEntityMapper userEntityMapper) {
		return new TodoEntityMapper(userEntityMapper);
	}

}