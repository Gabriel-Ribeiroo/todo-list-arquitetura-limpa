package com.ribeiro.clean_architecture_todo_list.adapters.controllers;

import com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.user.UserResponseDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.exceptions.ExceptionResponse;
import com.ribeiro.clean_architecture_todo_list.adapters.mappers.UserDTOMapper;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.user.GetUserByIdUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;

@Tag(name = "User Controller")
@SecurityRequirement(name = "jwt")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
	
	private GetUserByIdUseCase getUserById; 
	private UserDTOMapper userDTOMapper;

	@GetMapping("/me") 
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK."),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	public ResponseEntity<UserResponseDTO> getMe(JwtAuthenticationToken jwt) {
		var user = this.getUserById.execute(Long.parseLong(jwt.getName())); 

		return ResponseEntity.ok().body(this.userDTOMapper.toResponse(user)); 
	}

}