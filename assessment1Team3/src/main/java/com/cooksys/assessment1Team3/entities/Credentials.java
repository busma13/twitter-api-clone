package com.cooksys.assessment1Team3.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Credentials {
	private String password;
	private String username;
	private String email;
	private String phone;
}
