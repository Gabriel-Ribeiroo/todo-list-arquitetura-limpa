package com.ribeiro.clean_architecture_todo_list.adapters.controllers;

import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.CreateTodoUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.DeleteTodoByIdUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodoByIdUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.GetTodosUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.todo.UpdateTodoUseCase;
import com.ribeiro.clean_architecture_todo_list.domain.usecase.user.GetUserByIdUseCase;

import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo.UpdateTodoRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.request.todo.CreateTodoRequestDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.dtos.response.todo.TodoResponseDTO;
import com.ribeiro.clean_architecture_todo_list.adapters.exceptions.ExceptionResponse;
import com.ribeiro.clean_architecture_todo_list.adapters.mappers.TodoDTOMapper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Todo Controller")
@SecurityRequirement(name = "jwt")
@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

	private final CreateTodoUseCase createTodoUseCase;
	private final GetTodosUseCase getTodosUseCase; 
	private final GetTodoByIdUseCase getTodoByIdUseCase; 
	private final DeleteTodoByIdUseCase deleteTodoByIdUseCase; 
	private final UpdateTodoUseCase updateTodoUseCase; 
	private final GetUserByIdUseCase getUserByIdUseCase; 
	private final TodoDTOMapper mapper; 
	
	@GetMapping 
	@Operation(summary = "Lista todas as tarefas")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK."),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	ResponseEntity<List<TodoResponseDTO>> getAll(JwtAuthenticationToken jwt) {
		var responseDTO = mapper.toResponseList(this.getTodosUseCase.execute(
			Long.parseLong(jwt.getName())
		));

		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Listar tarefa por id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK."),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "400", 
			description = "Cheque as informações e tente novamente.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "404", 
			description = "Tarefa não encontrada.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	ResponseEntity<TodoResponseDTO> getById(@PathVariable Long id, JwtAuthenticationToken jwt) {
		var todoResponseDTO = mapper.toResponse(this.getTodoByIdUseCase.execute(
			id,
			Long.parseLong(jwt.getName()))
		);

		return ResponseEntity.ok(todoResponseDTO);
	}


	@PostMapping
	@Operation(summary = "Criar tarefa")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description= "OK."),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.", 
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "400", 
			description = "Cheque as informações e tente novamente.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	ResponseEntity<TodoResponseDTO> create(
		@Valid @RequestBody CreateTodoRequestDTO createTodoRequest, 
		JwtAuthenticationToken jwt
	) {		
		var user = this.getUserByIdUseCase.execute(Long.parseLong(jwt.getName())); 

		var todo = mapper.toDomain(createTodoRequest, user);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body( mapper.toResponse(this.createTodoUseCase.execute(todo)) );
	}
	

	@PatchMapping("/{id}")
	@Operation(summary = "Atualizar tarefa")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK."),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "400", 
			description = "Cheque as informações e tente novamente.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	ResponseEntity<TodoResponseDTO> update(
		@PathVariable Long id, 
		@Valid @RequestBody UpdateTodoRequestDTO updateTodoRequest,  
		JwtAuthenticationToken jwt
	) {
		var todo = this.getTodoByIdUseCase.execute(id, Long.parseLong(jwt.getName())); 
		var newData = this.mapper.toDomain(updateTodoRequest); 
		
		var updatedTodo = this.updateTodoUseCase.execute(todo, newData);  

		return ResponseEntity.ok().body(this.mapper.toResponse(updatedTodo)); 
	}


	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar tarefa")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description= "OK"),
		@ApiResponse(
			responseCode = "401", 
			description = "É necessário estar autenticado.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "400", 
			description = "Cheque as informações e tente novamente.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		),
		@ApiResponse(
			responseCode = "404", 
			description = "Tarefa não encontrada.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	ResponseEntity<TodoResponseDTO> deleteById(@PathVariable Long id, JwtAuthenticationToken jwt) {
		var todo = this.getTodoByIdUseCase.execute(id, Long.parseLong(jwt.getName())); 
		
		this.deleteTodoByIdUseCase.execute(id); 

		return ResponseEntity.ok(mapper.toResponse(todo));
	}

}