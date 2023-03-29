package com.cooksys.assessment1Team3.mappers;
import java.util.List;
import org.mapstruct.Mapper;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserResponseDto entityToDto(User entity);
	
}
