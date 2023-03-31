package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.dtos.*;
import com.cooksys.assessment1Team3.entities.Credentials;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.exceptions.NotAuthorizedException;
import com.cooksys.assessment1Team3.mappers.HashtagMapper;
import com.cooksys.assessment1Team3.mappers.TweetMapper;
import com.cooksys.assessment1Team3.mappers.UserMapper;
import com.cooksys.assessment1Team3.repositories.TweetRepository;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HashtagMapper hashtagMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {

        List<Tweet> tweets = tweetRepository.findAllByDeletedFalse().stream()
                .sorted(Comparator.comparing(Tweet::getPosted).reversed())
                .collect(Collectors.toList());

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        String username = tweetRequestDto.getCredentials().getUsername();
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User: " + username + " doesn't not exist or inactive.");
        }
        Tweet tweetToSave = new Tweet();
        tweetToSave.setAuthor(optionalUser.get());
        tweetToSave.setContent(tweetRequestDto.getContent());

        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToSave));
    }

    @Override
    public TweetResponseDto repostTweet(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweet = new Tweet();
        Tweet usedTweet = tweetRepository.findById(id).get();
        if(usedTweet.isDeleted() || usedTweet == null ) {
            throw new NotFoundException("We can't find a tweet with the id of "
                    + id + " in our database.");
        }
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(tweetRequestDto.getCredentials().getUsername());
        tweet.setAuthor(optionalUser.get());
        tweet.setContent(usedTweet.getContent());
        tweet.setPosted(new Timestamp(System.currentTimeMillis()));
        tweet.setRepostOf(usedTweet);
        tweetRepository.saveAndFlush(tweet);
        return tweetMapper.entityToDto(tweet);
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

    @Override
    public List<UserResponseDto> getTweetLikesByTweetId(Long id) {
        Tweet tweet = getTweet(id);
        if(tweet.isDeleted() || tweet == null) {
            throw new NotFoundException("Tweet with id " + id + " was not found in our database.");
        }
        return userMapper.entitiesToDtos(tweet.getLikes());
    }

    @Override
    public List<HashtagDto> getTweetTagsByTweetId(Long id) {
        Tweet tweet = getTweet(id);
        if(tweet.isDeleted() || tweet == null) {
            throw new NotFoundException("Tweet with id " + id + " was not found in our database.");
        }
        return hashtagMapper.entitiesToDtos(tweet.getHashtags());
    }

    @Override
    public TweetResponseDto getTweetContextByTweetId(Long id) {
        Tweet tweet = getTweet(id);
        if(tweet.isDeleted() || tweet == null) {
            throw new NotFoundException("Tweet with id " + id + " was not found in our database.");
        }
        return tweetMapper.entityToDto(tweet.getContent());
    }

	public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
		Optional<Tweet> optionalTweet = tweetRepository.findById(id);

		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("We can't find a tweet with the id of " + id + " in our database.");
		}
  
    Tweet tweet = optionalTweet.get();
		Credentials authorCredentials = tweet.getAuthor().getCredentials();
		if (!authorCredentials.getUsername().equals(credentialsDto.getUsername())
				|| !authorCredentials.getPassword().equals(credentialsDto.getPassword())) {
			throw new NotAuthorizedException("You do not have proper credentials to delete this tweet.");
    }
    
    tweet.setDeleted(true);

		return tweetMapper.entityToDto(tweet);
	}
	
    public List<TweetResponseDto> getUserTweets(String username) {
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with username of " + username + " was not found in our database.");
        } else {
            User user =  optionalUser.get();
            List<Tweet> userTweets =  user.getTweets();
            userTweets = userTweets.stream().filter(t -> !t.isDeleted()).collect(Collectors.toList());
            Collections.sort(userTweets, Comparator.comparing(Tweet::getPosted).reversed());
            return tweetMapper.entitiesToResponseDtos(tweetRepository.saveAllAndFlush(userTweets));
        }
    }
}