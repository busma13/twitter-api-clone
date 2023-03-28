package com.cooksys.assessment1Team3.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.dtos.HashtagDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.service.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
public class HashtagController {

	private final HashtagService hashtagService;

	public List<HashtagDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}

	public List<TweetResponseDto> getTweetsByHashtag(@PathVariable String label) {
		return hashtagService.getTweetsByHashtag();
	}

}
