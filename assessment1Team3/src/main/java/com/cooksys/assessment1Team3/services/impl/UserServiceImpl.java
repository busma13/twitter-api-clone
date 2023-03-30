package com.cooksys.assessment1Team3.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.repositories.TweetRepository;
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
	private final TweetMapper tweetMapper;
	private final ValidateService validateService;
	private final TweetRepository tweetRepository;

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
		User user = getUser(username);
		if (!validateService.validateUserExists(username) || user.isDeleted()) {
			throw new NotFoundException("There is no active user with name " + username);
		}
		List<TweetResponseDto> tweets = getUserTweets(username);
		System.out.println(tweets);
		for (User follow : user.getFollowing()) {
			tweets.addAll(getUserTweets(follow.toString()));
		}
		tweets.sort((e1, e2) -> e1.getPosted().compareTo(e2.getPosted()));
		Collections.reverse(tweets);
		return tweets;
	}

	private List<TweetResponseDto> getUserTweets(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<UserResponseDto> getUserFollowing(String username) {
		User user = getUser(username);

		List<User> activeUserFollowing = user.getFollowing().stream()
				.filter(user1 -> ! user1.isDeleted())
				.collect(Collectors.toList());

		if (activeUserFollowing == null || activeUserFollowing.isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not have any following.");
		}
		return userMapper.entitiesToDtos(activeUserFollowing);
	}

	@Override
	public List<UserResponseDto> getUserFollowers(String username) {
		User user = getUser(username);

		List<User> activeUserFollowers = user.getFollowers().stream()
				.filter(user1 -> ! user1.isDeleted())
				.collect(Collectors.toList());
		if (activeUserFollowers == null || activeUserFollowers.isEmpty()) {
			throw new NotFoundException("User with username of "
					+ username + " does not have any followers.");
		}
		return userMapper.entitiesToDtos(activeUserFollowers);
	}

	@Override
	public List<TweetResponseDto> getMentions(String username) {
		User user = getUser(username);

		List<Tweet> tweets =
				tweetRepository.findByContentContainingAndDeletedFalse("#" + user.getCredentials().getUsername());

		Collections.sort(tweets, Comparator.comparing(Tweet::getPosted).reversed());

		return tweetMapper.entitiesToResponseDtos(tweets);
	}

}
