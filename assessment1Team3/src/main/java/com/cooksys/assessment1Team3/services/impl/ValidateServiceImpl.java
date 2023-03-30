package com.cooksys.assessment1Team3.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;

	@Override
	public boolean validateUserExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUserAvailable(String username) {
		for (User user : userRepository.findAll()) {
			if (username.equals(user.getCredentials().getUsername())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean validateTagExists(String label) {
		// TODO Auto-generated method stub
		return false;
	}
}
