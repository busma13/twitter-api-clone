package com.cooksys.assessment1Team3.services;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;

public interface TweetService {
    TweetResponseDto getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);
}
