package com.movie.backend.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movie.backend.entity.User;
import com.movie.backend.enums.Role;
import com.movie.backend.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StartUpConfig implements CommandLineRunner {

	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	@Override
	public void run(String... args) throws Exception {
		if(!userRepo.existsByEmail("admin@gmail.com")) {
			User user = new User();
			user.setUsername("admin123");
			user.setFirstName("admin1");
			user.setLastName("admin2");
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("123456"));
			user.setEmail("admin@gmail.com");
			userRepo.save(user);
		}
	}

}
