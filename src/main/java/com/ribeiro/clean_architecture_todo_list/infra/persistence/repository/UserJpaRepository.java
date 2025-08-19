package com.ribeiro.clean_architecture_todo_list.infra.persistence.repository;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}