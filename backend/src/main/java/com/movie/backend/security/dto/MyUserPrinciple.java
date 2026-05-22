package com.movie.backend.security.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.movie.backend.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RequiredArgsConstructor
@Setter
public class MyUserPrinciple implements UserDetails {

	private final User user;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole().name())) ;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	public String getUsernameValue() {
		return user.getUsername();
	}
	
	public String getRole() {
		return user.getRole().name();
	}
	public Long getId() {
		return user.getId();
	}
	
	public LocalDateTime getCreatedAt() {
		return user.getCreatedAt();
	}

}
