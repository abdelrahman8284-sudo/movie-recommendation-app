package com.movie.backend.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.backend.dto.UserRegisterDto;
import com.movie.backend.dto.UserResponseDto;
import com.movie.backend.entity.User;
import com.movie.backend.enums.Role;
import com.movie.backend.exception.AlreadyExistsException;
import com.movie.backend.mapper.UserMapper;
import com.movie.backend.repository.UserRepo;
import com.movie.backend.security.dto.AuthResponse;
import com.movie.backend.security.dto.MyUserPrinciple;
import com.movie.backend.security.dto.UserLogin;
import com.movie.backend.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	private final UserMapper mapper;
	private final AuthenticationManager manager;
	private final JwtService jwtService;

	public UserResponseDto register(UserRegisterDto dto) {
		if(userRepo.existsByEmail(dto.getEmail())) {
			throw new AlreadyExistsException("This email already exists!");
		}
		User user = mapper.toUser(dto);
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setRole(Role.ROLE_USER);
		return mapper.toUserResponse(userRepo.save(user));
	}
	
	public AuthResponse login(UserLogin login) {
		return verify(login);
	}

	private AuthResponse verify(UserLogin login) {
		Authentication auth = manager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getEmail(), login.getPassword()));
		if(auth.isAuthenticated()) {
			var user = (MyUserPrinciple)auth.getPrincipal();
			String token = jwtService.generateToken(user);
			String authority = user.getRole();
			UserResponseDto userResponse = UserResponseDto.builder()
					.id(user.getId())
					.email(user.getUsername())
					.role(Role.valueOf(authority))
					.createdAt(user.getCreatedAt())
					.username(user.getUsernameValue())
					.build();
			AuthResponse response = AuthResponse.builder()
					.token(token)
					.success(true)
					.message("Login successfully")
					.user(userResponse)
					.build();
			return response;
		}
		throw new BadCredentialsException("Invalid email or password!");
	}
}
