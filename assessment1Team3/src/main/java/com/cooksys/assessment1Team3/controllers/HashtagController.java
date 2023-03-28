package com.cooksys.assessment1Team3.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.entities.Hashtag;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.service.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
public class HashtagController {

	private final HashtagService hashtagService;

	public List<Hashtag> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}

	public List<Tweet> getTweetsByHashtag(@PathVariable String label) {
		return hashtagService.getTweetsByHashtag();
	}

}
