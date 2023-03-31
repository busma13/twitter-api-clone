package com.cooksys.assessment1Team3.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Credentials;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.exceptions.NotAuthorizedException;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.repositories.TweetRepository;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	private final UserRepository userRepository;

	@Override
	public List<TweetResponseDto> getAllTweets() {

		List<Tweet> tweets = tweetRepository.findAllByDeletedFalse().stream()
				.sorted(Comparator.comparing(Tweet::getPosted).reversed()).collect(Collectors.toList());

		return tweetMapper.entitiesToResponseDtos(tweets);
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {

		String username = tweetRequestDto.getCredentials().getUsername();
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		Tweet tweetToSave = new Tweet();
		tweetToSave.setAuthor(optionalUser.get());
		tweetToSave.setContent(tweetToSave.getContent());

		return tweetMapper.entityToDto(tweetToSave);
	}

	@Override
	public Tweet getTweet(Long id) {
		Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);

		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("We can't find a tweet with the id of " + id + " in our database.");
		}
		return optionalTweet.get();
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {

		return tweetMapper.entityToDto(getTweet(id));
	}

	@Override
	public void addLikeToTweet(Long id, CredentialsDto credentials) {
		Tweet tweetToBeLiked = getTweet(id);
		String username = credentials.getUsername();
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("User with username of " + username + " was not found in our database.");
		} else {
			tweetToBeLiked.getLikes().add(optionalUser.get());
			optionalUser.get().getLikedTweets().add(tweetToBeLiked);

			tweetRepository.saveAndFlush(tweetToBeLiked);
			userRepository.saveAndFlush(optionalUser.get());
		}
	}

	public List<TweetResponseDto> getUserTweets(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto deleteTweet(Long id, Credentials credentials) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(id);

		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("We can't find a tweet with the id of " + id + " in our database.");
		}

		Tweet tweet = optionalTweet.get();
		Credentials authorCredentials = tweet.getAuthor().getCredentials();
		if (!authorCredentials.getUsername().equals(credentials.getUsername())
				|| !authorCredentials.getPassword().equals(credentials.getPassword())) {
			throw new NotAuthorizedException("You do not have proper credentials to delete this tweet.");
		}

		tweet.setDeleted(true);

		return tweetMapper.entityToDto(tweet);
	}

}
