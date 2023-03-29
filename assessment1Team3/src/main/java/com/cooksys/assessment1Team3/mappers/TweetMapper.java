package com.cooksys.assessment1Team3.mappers;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.entities.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    TweetResponseDto entityToResponseDto(Tweet tweet);
}
