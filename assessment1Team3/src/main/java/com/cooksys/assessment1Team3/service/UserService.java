package com.cooksys.assessment1Team3.service;

import java.util.List;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;

public interface UserService {
	UserResponseDto getUser(Long id);

	UserResponseDto validateUser(Long id);

	UserResponseDto modifyUser(long id);

	UserResponseDto deleteUser(long id);

	UserResponseDto getUserTweets(Long id);

	UserResponseDto getTweet(Long id);
}
