package com.movie.backend.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.backend.entity.User;
import com.movie.backend.repository.UserRepo;
import com.movie.backend.security.dto.MyUserPrinciple;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Email not found!"));	
		return new MyUserPrinciple(user);
	}

}
