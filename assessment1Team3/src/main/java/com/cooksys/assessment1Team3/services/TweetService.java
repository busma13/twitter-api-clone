package com.cooksys.assessment1Team3.services;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    Tweet getTweet(Long id);

    TweetResponseDto getTweetById(Long id);

    void addLikeToTweet(Long id, CredentialsDto credentials);

    List<TweetResponseDto> getUserTweets(String username);
}
