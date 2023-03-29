package com.cooksys.assessment1Team3.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsDto {
	private String password;
	private String username;
	private String email;
	private String phone;
}
