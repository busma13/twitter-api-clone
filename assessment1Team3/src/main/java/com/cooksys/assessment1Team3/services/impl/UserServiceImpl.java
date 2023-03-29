package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserResponseDto getUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto validateUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto modifyUser(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto deleteUser(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto getUserTweets(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto getTweet(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
