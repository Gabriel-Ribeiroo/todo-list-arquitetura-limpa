package com.ribeiro.clean_architecture_todo_list.adapters.mappers;

import com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.user.UserResponseDTO;
import com.ribeiro.clean_architecture_todo_list.domain.entity.User;

public class UserDTOMapper {
	
	public UserResponseDTO toResponse(User user) {
		return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail()); 
	}

}
