package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.dto.TweetResponseDto;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.repositories.TweetRepository;
import com.cooksys.assessment1Team3.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public TweetResponseDto getAllTweets() {

        return tweetMapper.entityToResponseDto(tweetRepository.findAllByDeletedFalse());
    }
}
