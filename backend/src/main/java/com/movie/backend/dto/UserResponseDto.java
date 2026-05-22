package com.movie.backend.dto;

import java.time.LocalDateTime;

import com.movie.backend.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data@Builder
public class UserResponseDto {

	private Long id;
	private String username;
	private String email;
	private LocalDateTime createdAt;
	private Role role;
}
