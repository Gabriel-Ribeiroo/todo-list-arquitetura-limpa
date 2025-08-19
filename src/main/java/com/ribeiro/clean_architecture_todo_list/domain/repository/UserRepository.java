package com.ribeiro.clean_architecture_todo_list.domain.repository;
import java.util.Optional;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

public interface UserRepository {
	public Optional<User> findUserByEmail(String email); 
	public Optional<User> findById(Long id); 
	public User save(User user); 
}
