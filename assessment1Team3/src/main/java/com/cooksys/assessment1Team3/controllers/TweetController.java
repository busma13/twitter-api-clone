package com.cooksys.assessment1Team3.controllers;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
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

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLikeToTweet(@PathVariable(name = "id") Long id, @RequestBody CredentialsDto credentials) {
        tweetService.addLikeToTweet(id, credentials);
    }
    

//	THIS GOES IN TWEET CONTROLLER
//	@GetMapping("tweets/{id}/context")
//	public UserResponseDto getTweet(Long id) {
//		return userService.getTweet(id);
//	}
}
