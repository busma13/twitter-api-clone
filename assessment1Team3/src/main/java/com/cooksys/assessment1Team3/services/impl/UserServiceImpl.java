package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserRequestDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.Profile;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.exceptions.BadRequestException;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.exceptions.UserAlreadyExistException;
import com.cooksys.assessment1Team3.mappers.ProfileMapper;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.mappers.UserMapper;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.TweetService;
import com.cooksys.assessment1Team3.services.UserService;
import com.cooksys.assessment1Team3.services.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TweetMapper tweetMapper;
	private final TweetService tweetService;
	private final ValidateService validateService;
	private final ProfileMapper profileMapper;

	private void validateUserRequest(UserRequestDto userRequestDto) {
		if (userRequestDto.getCredentials().getUsername() == null ||
				userRequestDto.getCredentials().getPassword() == null ||
				userRequestDto.getProfile().getFirstName() == null ||
				userRequestDto.getProfile().getLastName() == null ||
				userRequestDto.getProfile().getEmail() == null ||
				userRequestDto.getProfile().getPhone() == null) {
			throw new BadRequestException("You must provide all the required fileds for the request.");
		}
	}

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
		Collections.sort(tweets, Comparator.comparing(TweetResponseDto::getPosted).reversed());
		return tweets;
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

		List<Tweet> tweets = user.getMentionedTweets().stream()
				.filter(tweet -> Objects.nonNull(tweet.getContent()))
				.sorted(Comparator.comparing(Tweet::getPosted).reversed())
				.collect(Collectors.toList());

		return tweetMapper.entitiesToResponseDtos(tweets);
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequest) {
		validateUserRequest(userRequest);
		String username = userRequest.getCredentials().getUsername();
		Optional<User> user = userRepository.findByCredentialsUsername(username);
		if (user.isPresent()) {
			if (user.get().isDeleted() &&
					user.get().getCredentials().getPassword().equals(userRequest.getCredentials().getPassword())) {
				user.get().setDeleted(false);
				return userMapper.entityToDto(userRepository.saveAndFlush(user.get()));
			} else {
				throw new UserAlreadyExistException("Username: " + username + " is already taken!");
			}
		}  else {
			return userMapper.entityToDto(
					userRepository.saveAndFlush(userMapper.requestDtoToEntity(userRequest)));
		}
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
		if (!validateService.validateUserExists(username)) {
			throw new NotFoundException("User with username of " + username + " not found.");
		}
		User deletedUser = getUser(username);
		deletedUser.setDeleted(true);
		return userMapper.entityToDto(userRepository.saveAndFlush(deletedUser));
	}

	@Override
	public List<UserResponseDto> getMentions(Long id) {
		Tweet tweet = tweetService.getTweet(id);
		List<User> mentionedUsers = tweet.getMentionedUsers();
		mentionedUsers = mentionedUsers.stream().filter(u -> !u.isDeleted()).collect(Collectors.toList());
		return userMapper.entitiesToDtos(mentionedUsers);
	}

}
