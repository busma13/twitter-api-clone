package com.cooksys.assessment1Team3.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping
	public UserResponseDto getUser(Long id) {
		return userService.getUser(id);
	}
	
//	THIS GOES IN VALIDATE CONTROLLER
//	@GetMapping("validate/username/exists/@{username}")
//	public UserResponseDto validateUser(Long id) {
//		return userService.validateUser(id);
//	}
	
	// Use @PathVariable for data received in path. Arguments should be re-evaluated
	@PatchMapping("/@{username}")
	public UserResponseDto modifyUser(long id) {
		return userService.modifyUser(id);
	}
	
	// Use @PathVariable for data received in path. Use Long wrapper class for DataType in arguments
	@DeleteMapping("/@{username}")
	public UserResponseDto deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	
	
//	THIS GOES IN TWEET CONTROLLER
//	@GetMapping("tweets/{id}/context")
//	public UserResponseDto getTweet(Long id) {
//		return userService.getTweet(id);
//	}
	
	@GetMapping("/@{username}/tweets")
	public UserResponseDto getUserTweets(Long id) {
		return userService.getUserTweets(id);
	}

}