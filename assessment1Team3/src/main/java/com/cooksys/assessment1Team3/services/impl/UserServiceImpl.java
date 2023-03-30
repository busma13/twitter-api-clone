package com.cooksys.assessment1Team3.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserRequestDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.Profile;
import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.mappers.ProfileMapper;
import com.cooksys.assessment1Team3.mappers.UserMapper;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.TweetService;
import com.cooksys.assessment1Team3.services.UserService;
import com.cooksys.assessment1Team3.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ValidateService validateService;
	private final TweetService tweetService;
	private final ProfileMapper profileMapper;

	@Override
	public User getUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not exist in our database.");
		}
		return optionalUser.get();
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {
		return userMapper.entityToDto(getUser(username));
	}

	@Override
	public List<UserResponseDto> getUsers() {
		return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
	}

	@Override
	public List<TweetResponseDto> getUserFeed(String username) {
		User user = getUser(username);
		if (!validateService.validateUserExists(username) || user.isDeleted()) {
			throw new NotFoundException("There is no active user with name " + username);
		}
		List<TweetResponseDto> tweets = tweetService.getUserTweets(username);
		System.out.println(tweets);
		for (User follow : user.getFollowing()) {
			tweets.addAll(tweetService.getUserTweets(follow.toString()));
		}
		tweets.sort((e1, e2) -> e1.getPosted().compareTo(e2.getPosted()));
		Collections.reverse(tweets);
		return tweets;
	}

	@Override
	public List<UserResponseDto> getUserFollowing(String username) {
		User user = getUser(username);

		if (user.getFollowing() == null || user.getFollowing().isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not have any following.");
		}
		return userMapper.entitiesToDtos(user.getFollowing());
	}

	@Override
	public List<UserResponseDto> getUserFollowers(String username) {
		User user = getUser(username);

		if (user.getFollowers() == null || user.getFollowers().isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not have any followers.");
		}

		return userMapper.entitiesToDtos(user.getFollowers());
	}

	@Override
	public UserResponseDto modifyUser(String username, UserRequestDto body) {
		User user = getUser(username);
		// validate credential
		Profile mappedUserProfile = profileMapper.dtoToEntity(body.getProfile());
		if (mappedUserProfile.getEmail() != null) {
			user.getProfile().setEmail(mappedUserProfile.getEmail());
		}
		if (mappedUserProfile.getFirstName() != null) {
			user.getProfile().setFirstName(mappedUserProfile.getFirstName());
		}
		if (mappedUserProfile.getLastName() != null) {
			user.getProfile().setLastName(mappedUserProfile.getLastName());
		}
		if (mappedUserProfile.getPhone() != null) {
			user.getProfile().setPhone(mappedUserProfile.getPhone());
		}
		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto deleteUser(String username) {
		// how do i check if user credential doesn't match??
		if (!validateService.validateUserExists(username)) {
			throw new NotFoundException("User with username of " + username + " not found.");
		}
		User deletedUser = getUser(username);
		deletedUser.setDeleted(true);
		return userMapper.entityToDto(userRepository.saveAndFlush(deletedUser));
	}

}
