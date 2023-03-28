package com.cooksys.assessment1Team3.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.assessment1Team3.dtos.HashtagDto;
import com.cooksys.assessment1Team3.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	HashtagDto entityToDto(Hashtag hashtag);
	
	List<HashtagDto> entitiesToDtos(List<Hashtag> hashtags);
}
