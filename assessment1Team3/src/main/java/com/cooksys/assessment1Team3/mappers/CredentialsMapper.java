package com.cooksys.assessment1Team3.mappers;

import org.mapstruct.Mapper;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.entities.Credentials;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsDto entityToDto(Credentials credentials);

    Credentials dtoToEntity(CredentialsDto dto);
}
