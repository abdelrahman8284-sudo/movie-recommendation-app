package com.movie.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {

	@NotBlank(message = "firstName required")
	private String firstName;
	@NotBlank(message = "lastName required")
	private String lastName;
	@NotBlank(message="username shouldnot be empty")
	private String username;
	@Email
	@NotBlank(message = "Email shouldnot be empty")
	private String email;
	@Size(min = 6,max=10)
	@NotBlank
	private String password;
}
