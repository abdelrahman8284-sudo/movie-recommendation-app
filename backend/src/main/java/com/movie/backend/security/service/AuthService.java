package com.movie.backend.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.backend.dto.UserRegisterDto;
import com.movie.backend.dto.UserResponseDto;
import com.movie.backend.entity.User;
import com.movie.backend.enums.Role;
import com.movie.backend.exception.AlreadyExistsException;
import com.movie.backend.mapper.UserMapper;
import com.movie.backend.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	private final UserMapper mapper;

	public UserResponseDto register(UserRegisterDto dto) {
		if(userRepo.existsByEmail(dto.getEmail())) {
			throw new AlreadyExistsException("This email already exists!");
		}
		User user = mapper.toUser(dto);
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setRole(Role.ROLE_USER);
		return mapper.toUserResponse(userRepo.save(user));
	}
}
