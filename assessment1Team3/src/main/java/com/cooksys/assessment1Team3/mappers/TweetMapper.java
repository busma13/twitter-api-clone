package com.cooksys.assessment1Team3.mappers;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;
import com.cooksys.assessment1Team3.entities.User;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet tweet);

    Tweet responseDtoToEntity(TweetResponseDto tweetResponseDto);

    Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);
    
    List<UserResponseDto> entitiesToDtos(List<User> tweets);

	TweetResponseDto entityToDto(String content);

	TweetResponseDto requestDtoToResponseDto(TweetRequestDto tweetRequestDto);

}
