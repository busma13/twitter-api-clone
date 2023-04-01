package com.cooksys.assessment1Team3.services;

import java.util.List;

import com.cooksys.assessment1Team3.dtos.ContextDto;
import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.dtos.HashtagDto;
import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;

public interface TweetService {
	List<TweetResponseDto> getAllTweets();

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

	Tweet getTweet(Long id);

	TweetResponseDto getTweetById(Long id);

	void addLikeToTweet(Long id, CredentialsDto credentials);

	List<TweetResponseDto> getUserTweets(String username);

	List<UserResponseDto> getTweetLikesByTweetId(Long id);

	List<HashtagDto> getTweetTagsByTweetId(Long id);

	ContextDto getTweetContextByTweetId(Long id);

	TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto);

	TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto);

	TweetResponseDto createReplyToTweet(Long id, TweetRequestDto tweetRequestDto);

	List<TweetResponseDto> getRepliesToTweet(Long id);

	List<TweetResponseDto> getRepostsOfTweet(Long id);


}
