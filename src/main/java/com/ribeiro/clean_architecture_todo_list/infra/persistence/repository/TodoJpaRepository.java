package com.ribeiro.clean_architecture_todo_list.infra.persistence.repository;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.entity.TodoEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoJpaRepository extends JpaRepository<TodoEntity, Long> {
	List<TodoEntity> getByUserId(Long userId);
	Optional<TodoEntity> getByIdAndUserId(Long id, Long userId);  
}