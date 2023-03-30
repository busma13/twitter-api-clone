package com.cooksys.assessment1Team3.services.impl;

import com.cooksys.assessment1Team3.entities.User;
import com.cooksys.assessment1Team3.exceptions.NotFoundException;
import com.cooksys.assessment1Team3.repositories.UserRepository;
import com.cooksys.assessment1Team3.services.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
	private final UserRepository userRepository;

	@Override
	public boolean validateUserExists(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);

		if (!optionalUser.isEmpty()) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUserAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean validateTagExists(String label) {
		// TODO Auto-generated method stub
		return false;
	}
}
