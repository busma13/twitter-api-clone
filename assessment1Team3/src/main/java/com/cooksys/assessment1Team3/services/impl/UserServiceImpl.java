package com.cooksys.assessment1Team3.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.mappers.UserMapper;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;

    @Override
    public UserResponseDto getUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto validateUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto modifyUser(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto deleteUser(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto getUserTweets(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto getTweet(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List<UserResponseDto> getUsers() {
		return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
	}

}
