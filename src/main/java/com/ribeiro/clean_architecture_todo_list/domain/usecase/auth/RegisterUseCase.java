package com.ribeiro.clean_architecture_todo_list.domain.usecase.auth;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

public interface RegisterUseCase {
	public User execute(User user); 
}
