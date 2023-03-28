package com.cooksys.assessment1Team3.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/???")
public class UserController {
	private final UserService userService;
	
	@GetMapping
	public UserResponseDto getUser(Long id) {
		return userService.getUser(id);
	}
	
	@GetMapping("validate/username/exists/@{username}")
	public UserResponseDto validateUser(Long id) {
		return userService.validateUser(id);
	}
	
	@PatchMapping("users/@{username}")
	public UserResponseDto modifyUser(long id) {
		return userService.modifyUser(id);
	}
	
	@DeleteMapping("users/@{username}")
	public UserResponseDto deleteUser(long id) {
		return userService.deleteUser(id);
	}
	
	@GetMapping("tweets/{id}/context")
	public UserResponseDto getTweet(Long id) {
		return userService.getTweet(id);
	}
	
	@GetMapping("users/@{username}/tweets")
	public UserResponseDto getUserTweets(Long id) {
		return userService.getUserTweets(id);
	}

}