package com.movie.backend.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.movie.backend.dto.UserResponseDto;

import lombok.Builder;
import lombok.Data;
@Data@JsonInclude(Include.NON_NULL)@Builder
public class AuthResponse {

	private boolean success;
	private String message;
	private String token;
	private UserResponseDto user;
}
