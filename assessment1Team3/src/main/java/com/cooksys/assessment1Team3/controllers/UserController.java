package com.cooksys.assessment1Team3.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/{username}")
	public UserResponseDto getUser(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	
	@GetMapping
	public List<UserResponseDto> getUsers() {
		return userService.getUsers();
	}
	
	
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
	
	@GetMapping("/@{username}/tweets")
	public UserResponseDto getUserTweets(Long id) {
		return userService.getUserTweets(id);
	}

	@GetMapping("/@{username}/feed")
	public List<TweetResponseDto> getUserFeed(@PathVariable String username) {
		return userService.getUserFeed(username);
	}
	
	@GetMapping("/@{username}/following")
	public List<UserResponseDto> getUserFollowing(@PathVariable String username) {
		return userService.getUserFollowing(username);
	}

	@GetMapping("@{username}/followers")
	public List<UserResponseDto> getUserFollowers(@PathVariable String username) {
		return userService.getUserFollowers(username);
	}
}