package com.ribeiro.clean_architecture_todo_list.infra.persistence.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "users")
@NoArgsConstructor
@Getter
public class UserEntity implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 

	@Column(nullable = false)
	private String username; 

	@Column(nullable = false, unique=true) 
	private String email; 

	@Column(nullable = false)
	private String password; 

	@OneToMany(
		mappedBy = "user",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	private List<TodoEntity> todos = new ArrayList<>(); 

	public UserEntity(Long id, String username, String email, String password) {
		this.id = id; 
		this.username = username; 
		this.email = email; 
		this.password = password; 
	}

	public UserEntity(String username, String email, String password) {
		this.username = username; 
		this.email = email; 
		this.password = password; 
	}

	@Override
	public String getUsername() {
		return this.username;  
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(); 
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; 
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; 
	}

	@Override
	public boolean isEnabled() {
		return true; 
	}

}