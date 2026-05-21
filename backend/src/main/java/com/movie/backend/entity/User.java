package com.movie.backend.entity;

import org.hibernate.annotations.ColumnDefault;

import com.movie.backend.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@AllArgsConstructor@NoArgsConstructor@Setter@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String username;
	@NotBlank
	@Email
	@Column(unique = true,nullable = false)
	private String email;
	@NotBlank
	@Size(min = 6, max = 10)
	private String password;
	@Enumerated(EnumType.STRING)
	@ColumnDefault("ROLE_USER")
	private Role role;
}
