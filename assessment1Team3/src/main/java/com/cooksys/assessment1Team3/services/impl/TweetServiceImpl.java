package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.repositories.TweetRepository;
import com.cooksys.assessment1Team3.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {

        List<Tweet> tweets = tweetRepository.findAllByDeletedFalse().stream()
                .sorted(Comparator.comparing(Tweet::getPosted).reversed())
                .collect(Collectors.toList());

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        return null;
    }

    @Override
    public Tweet getTweet(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("We can't find a tweet with the id of "
                    + id + " in our database.");
        }
        return optionalTweet.get();
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {

        return tweetMapper.entityToDto(getTweet(id));
    }

    @Override
    public List<TweetResponseDto> getUserTweets(String username) {
        // TODO Auto-generated method stub
        return null;
    }
}
