package com.movie.backend.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor@Builder
public class UserLogin {

	private String email;
	private String password;
}
