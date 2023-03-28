package com.cooksys.assessment1Team3.controllers;

import com.cooksys.assessment1Team3.dto.TweetResponseDto;
import com.cooksys.assessment1Team3.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public TweetResponseDto getAllTweets() {
        return tweetService.getAllTweets();
    }
}
