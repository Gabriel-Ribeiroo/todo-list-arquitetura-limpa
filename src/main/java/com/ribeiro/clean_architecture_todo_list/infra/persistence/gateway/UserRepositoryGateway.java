package com.ribeiro.clean_architecture_todo_list.infra.persistence.gateway;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.repository.UserRepository;

import com.ribeiro.clean_architecture_todo_list.infra.persistence.mapper.UserEntityMapper;
import com.ribeiro.clean_architecture_todo_list.infra.persistence.repository.UserJpaRepository;

import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
public class UserRepositoryGateway implements UserRepository {
		
	private final UserJpaRepository UserJpaRepository; 
	private final UserEntityMapper userEntityMapper;  

	@Override
	public User save(User user) {
		var userEntity = this.userEntityMapper.toEntity(user); 

		return this.userEntityMapper.toDomain(this.UserJpaRepository.save(userEntity)); 
		
	}

		@Override
		public Optional<User> findUserByEmail(String email) {
			var user = this.UserJpaRepository.findByEmail(email)
				.map(this.userEntityMapper::toDomain);
		
			return user; 
		}

		@Override 
		public Optional<User> findById(Long id) {
			var user = this.UserJpaRepository.findById(id)
				.map(this.userEntityMapper::toDomain);

			return user; 
		}

}
