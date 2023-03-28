package com.cooksys.assessment1Team3.dtos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

public class UserRequestDto {
	private String password;
	private String phone;
	private boolean deleted;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private Long id;
	
	@CreationTimestamp
    private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());
}
