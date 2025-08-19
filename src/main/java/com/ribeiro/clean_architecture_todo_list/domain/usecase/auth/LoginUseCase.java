package com.ribeiro.clean_architecture_todo_list.domain.usecase.auth;

public interface LoginUseCase {
	public String execute(String email, String password);
}
