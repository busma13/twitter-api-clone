 package com.cooksys.assessment1Team3.controllers;

import org.springframework.web.bind.annotation.*;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.TweetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable (name = "id") Long id) {
        return tweetService.getTweetById(id);
    }

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }
    
	@GetMapping("/@{username}/tweets")
	public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
		return tweetService.getUserTweets(username);
	}
    

//	THIS GOES IN TWEET CONTROLLER
//	@GetMapping("tweets/{id}/context")
//	public UserResponseDto getTweet(Long id) {
//		return userService.getTweet(id);
//	}
}
