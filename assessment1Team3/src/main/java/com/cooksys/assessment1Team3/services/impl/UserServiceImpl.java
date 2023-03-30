package com.cooksys.assessment1Team3.services.impl;

import java.util.List;
import java.util.Optional;

import com.cooksys.assessment1Team3.entities.User;
import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.mappers.UserMapper;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.UserService;
import com.cooksys.assessment1Team3.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ValidateService validateService;

	@Override
	public User getUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not exist in our database.");
		}
		// TODO Auto-generated method stub
		return optionalUser.get();
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {
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

	@Override
	public List<TweetResponseDto> getUserFeed(String username) {
		if (!validateService.validateUserExists(username) || getUser(username).isDeleted()) {
			throw new NotFoundException("There is no user with name " + username);
		}
		return null;
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

}
