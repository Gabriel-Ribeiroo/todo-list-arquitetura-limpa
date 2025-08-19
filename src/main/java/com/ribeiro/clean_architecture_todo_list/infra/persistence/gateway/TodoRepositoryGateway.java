package com.ribeiro.clean_architecture_todo_list.infra.persistence.gateway;

import com.ribeiro.clean_architecture_todo_list.domain.entity.Todo;
import com.ribeiro.clean_architecture_todo_list.domain.repository.TodoRepository;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper.TodoEntityMapper;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.repository.TodoJpaRepository;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TodoRepositoryGateway implements TodoRepository {

	private final TodoJpaRepository todoJpaRepository;
	private final TodoEntityMapper todoEntityMapper;

	@Override
	public List<Todo> getAll(Long userId) {
		var todoEntityList = this.todoJpaRepository.getByUserId(userId);

		return this.todoEntityMapper.toDomainList(todoEntityList);
	}

	@Override
	public Optional<Todo> getById(Long id, Long userId) {
		var todo = this.todoJpaRepository
			.getByIdAndUserId(id, userId)
			.map(this.todoEntityMapper::toDomain);

		return todo;
	}


	@Override
	public Todo save(Todo todo) {
		var todoEntity = todoEntityMapper.toEntity(todo);

		return todoEntityMapper.toDomain(todoJpaRepository.save(todoEntity));
	}


	@Override
	public void deleteById(Long id) {
		this.todoJpaRepository.deleteById(id);
	}

}