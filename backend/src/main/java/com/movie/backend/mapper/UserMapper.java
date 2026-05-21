package com.movie.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.movie.backend.dto.UserRegisterDto;
import com.movie.backend.dto.UserResponseDto;
import com.movie.backend.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User toUser(UserRegisterDto register);
	
	UserResponseDto toUserResponse(User user);
	
	List<UserResponseDto> toListDto(List<User> users);
}
