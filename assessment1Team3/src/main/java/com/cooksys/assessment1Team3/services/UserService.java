package com.cooksys.assessment1Team3.services;

import java.util.List;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;

public interface UserService {
	
	UserResponseDto getUser(String username);

	UserResponseDto modifyUser(long id);

	UserResponseDto deleteUser(long id);

	UserResponseDto getUserTweets(Long id);

	UserResponseDto getTweet(Long id);

	List<UserResponseDto> getUsers();

	List<TweetResponseDto> getUserFeed(String username);

}
