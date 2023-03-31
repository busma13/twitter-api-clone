package com.cooksys.assessment1Team3.controllers;

import com.cooksys.assessment1Team3.dtos.CredentialsDto;
import com.cooksys.assessment1Team3.dtos.TweetResponseDto;
import com.cooksys.assessment1Team3.dtos.UserRequestDto;
import com.cooksys.assessment1Team3.dtos.UserResponseDto;
import com.cooksys.assessment1Team3.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@GetMapping("/@{username}")
	public UserResponseDto getUserByUsername(@PathVariable String username) {
		System.out.println("Testing");
		return userService.getUserByUsername(username);
	}

	@GetMapping
	public List<UserResponseDto> getUsers() {
		return userService.getUsers();
	}

	@PatchMapping("/@{username}")
	public UserResponseDto modifyUser(@PathVariable String username, @RequestBody UserRequestDto body) {
		return userService.modifyUser(username, body);
	}

	@DeleteMapping("/@{username}")
	public UserResponseDto deleteUser(@PathVariable String username) {
		return  userService.deleteUser(username);
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

	@GetMapping("/@{username}/mentions")
	public List<TweetResponseDto> getMentions(@PathVariable String username) {
		return userService.getMentions(username);
	}

	@PostMapping
	public UserResponseDto createUser(@RequestBody UserRequestDto userRequest) {
		return userService.createUser(userRequest);
	}
	
	@PostMapping("/@{username}/follow")
	public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
		userService.followUser(username, credentialsDto);
	}

}