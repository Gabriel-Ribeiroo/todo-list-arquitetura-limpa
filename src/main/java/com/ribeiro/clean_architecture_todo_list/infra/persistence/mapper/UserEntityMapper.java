package com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.entity.UserEntity;

public class UserEntityMapper {

	public UserEntity toEntity(User user) {
		return new UserEntity(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()); 
	}

	public User toDomain(UserEntity userEntity) {
		return new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword());
	}

}
