package com.ribeiro.clean_architecture_todo_list.domain.security;

public interface AccessTokenGenerator {
	String generate(Long userId); 
}
