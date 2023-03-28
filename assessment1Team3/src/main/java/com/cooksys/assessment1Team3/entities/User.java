package com.cooksys.assessment1Team3.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {
	private String password;
	private String phone;
	private boolean deleted;
	private String firstName;
	private String lastName;
	
	@OneToMany
	@JoinColumn(name = "author")
	private String userName;
	
//	private Java.time.localDateTime joined;

	
	@OneToMany
	@JoinColumn(name = "user_id")
	@JoinColumn(name = "tweet_id")
	private String email;
	
	@OneToMany
	@JoinColumn(name = "follower_id")
	@JoinColumn(name = "following_id")
	@Id
	@GeneratedValue
	private Long id;
	
	
}