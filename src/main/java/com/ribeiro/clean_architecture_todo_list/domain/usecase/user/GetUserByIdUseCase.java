package com.ribeiro.clean_architecture_todo_list.domain.usecase.user;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

public interface GetUserByIdUseCase {
	User execute(Long userId);  
}