package com.ribeiro.clean_architecture_todo_list.adapters.controllers;

import com.ribeiro.clean_architecture_todo_list.domain.entity.User;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.LoginUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.auth.RegisterUseCase;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.auth.LoginRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.auth.RegisterRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.user.UserResponseDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.exceptions.ExceptionResponse;
import com.ribeiro.clean_architecture_todo_list.adapters.mappers.UserDTOMapper;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Auth Controller")
public class AuthController {

	private final RegisterUseCase registerUseCase; 
	private final LoginUseCase loginUseCase; 
	private final UserDTOMapper userDTOMapper; 
	
	@PostMapping("/register")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200", 
			description = "OK."
		),
		@ApiResponse(
			responseCode = "409", 
			description = "E-mail já está em uso.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerUserDTO) {
		var user = this.registerUseCase.execute(
			new User(registerUserDTO.username(), registerUserDTO.email(), registerUserDTO.password())
		); 

		return ResponseEntity.status(HttpStatus.CREATED).body(this.userDTOMapper.toResponse(user)); 
	}

	@PostMapping("/login")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK."),
		@ApiResponse(
			responseCode =  "400", 
			description = "E-mail ou senha inválidos.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	public ResponseEntity<Void> login(@Valid @RequestBody LoginRequestDTO logUserInDTO) {
		var accessToken = this.loginUseCase.execute(logUserInDTO.email(), logUserInDTO.password());
		
		var jwtCookie = ResponseCookie.from("accessToken", accessToken)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(Duration.ofHours(1))
			.sameSite("Strict")
			.build(); 

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).build();
	}

}