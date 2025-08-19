package com.ribeiro.clean_architecture_todo_list.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;

public interface TodoRepository {
	Todo save(Todo todo);
	List<Todo> getAll(Long userId); 
	Optional<Todo> getById(Long id, Long userId);  
	void deleteById(Long id);
}