package com.cooksys.assessment1Team3.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.dtos.HashtagDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.mappers.HashtagMapper;
import com.cooksys.assessment1Team3.repositories.HashtagRepository;
import com.cooksys.assessment1Team3.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetsByHashtag() {
		// TODO Auto-generated method stub
		return null;
	}

}
