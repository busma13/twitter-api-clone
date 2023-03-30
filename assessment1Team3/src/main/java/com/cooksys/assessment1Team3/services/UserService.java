package com.cooksys.assessment1Team3.services;

import java.util.List;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.User;

public interface UserService {

	User getUser(String username);
	
	UserResponseDto getUserByUsername(String username);

	UserResponseDto modifyUser(long id);

	UserResponseDto deleteUser(long id);

	UserResponseDto getUserTweets(Long id);

	UserResponseDto getTweet(Long id);

	List<UserResponseDto> getUsers();

	List<TweetResponseDto> getUserFeed(String username);

    List<UserResponseDto> getUserFollowing(String username);

	List<UserResponseDto> getUserFollowers(String username);
}
