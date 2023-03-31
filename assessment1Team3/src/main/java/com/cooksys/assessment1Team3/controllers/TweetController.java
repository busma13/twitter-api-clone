package com.cooksys.assessment1Team3.controllers;

import com.cooksys.assessment1Team3.dtos.*;
import com.cooksys.assessment1Team3.services.TweetService;
import com.cooksys.assessment1Team3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {


    private final TweetService tweetService;
    private final UserService userService;

    @GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable(name = "id") Long id) {
        return tweetService.getTweetById(id);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getMentions(@PathVariable Long id) {
        return userService.getMentions(id);
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

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.deleteTweet(id, credentialsDto);
    }

    @GetMapping("/@{id}/likes")
    public List<UserResponseDto> getTweetLikesByTweetId(@PathVariable Long id){
        return tweetService.getTweetLikesByTweetId(id);
    }

    @GetMapping("/@{id}/tags")
    public List<HashtagDto> getTweetTagsByTweetId(@PathVariable Long id){
        return tweetService.getTweetTagsByTweetId(id);
    }

    @GetMapping("/@{id}/context")
    public TweetResponseDto getTweetContextByTweetId(@PathVariable Long id){
        return tweetService.getTweetContextByTweetId(id);
    }

    @PostMapping("/@{id}/repost")
    public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.repostTweet(id, tweetRequestDto);
    }
    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getUserTweets(@PathVariable String username) {
        return tweetService.getUserTweets(username);
    }

}
