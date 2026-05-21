package com.movie.backend.dto;

import com.movie.backend.enums.Role;

import lombok.Data;

@Data
public class UserResponseDto {

	private Long id;
	private String username;
	private String email;
	private Role role;
}
