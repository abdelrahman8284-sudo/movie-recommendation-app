package com.movie.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.backend.dto.UserRegisterDto;
import com.movie.backend.dto.UserResponseDto;
import com.movie.backend.dto.response.ApiResponse;
import com.movie.backend.security.dto.AuthResponse;
import com.movie.backend.security.dto.UserLogin;
import com.movie.backend.security.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody@Valid UserRegisterDto register){
		UserResponseDto response = authService.register(register);
		return new ResponseEntity<>(
				new ApiResponse(true, "User register successfully", response)
				,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserLogin login){
		return ResponseEntity.ok(authService.login(login));
	}
}
