package com.cooksys.assessment1Team3.mappers;

import com.cooksys.assessment1Team3.dtos.TweetRequestDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    TweetResponseDto entityToResponseDto(Tweet tweet);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);

    Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);
}
