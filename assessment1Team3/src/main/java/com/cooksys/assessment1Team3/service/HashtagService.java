package com.cooksys.assessment1Team3.service;

import java.util.List;

import com.cooksys.assessment1Team3.entities.Hashtag;
import com.cooksys.assessment1Team3.entities.Tweet;

public interface HashtagService {

	List<Hashtag> getAllHashtags();

	List<Tweet> getTweetsByHashtag();

}
