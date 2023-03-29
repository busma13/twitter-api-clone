package com.cooksys.assessment1Team3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
	
	private final ValidateService validateService;
	
	@GetMapping("validate/username/exists/@{username}")
	public boolean validateUserExists(String username) {
		return validateService.validateUserExists(username);
	}

	@GetMapping("validate/username/available/@{username}")
	public boolean validateUserAvailable(String username) {
		return validateService.validateUserAvailable(username);
	}
	
	@GetMapping("validate/tag/exists/@{label}")
	public boolean validateTagExists(String label) {
		return validateService.validateTagExists(label);
	}
}
