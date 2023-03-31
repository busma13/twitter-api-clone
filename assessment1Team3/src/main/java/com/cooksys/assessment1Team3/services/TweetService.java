package com.cooksys.assessment1Team3.services;

import java.util.List;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Credentials;
import com.cooksys.assessment1Team3.entities.Tweet;

public interface TweetService {
	List<TweetResponseDto> getAllTweets();

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

	Tweet getTweet(Long id);

	TweetResponseDto getTweetById(Long id);

	void addLikeToTweet(Long id, CredentialsDto credentials);

	List<TweetResponseDto> getUserTweets(String username);

	TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto);

}
