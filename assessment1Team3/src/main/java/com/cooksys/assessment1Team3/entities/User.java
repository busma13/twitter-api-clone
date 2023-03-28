package com.cooksys.assessment1Team3.entities;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
	private String email;
	private String userName;
	
	@CreationTimestamp
	private Timestamp joined = Timestamp.valueOf(LocalDateTime.now());
	
	@OneToMany
	@JoinColumn(name = "follower_id")
	@JoinColumn(name = "following_id")
	@JoinColumn(name = "user_id")
	@JoinColumn(name = "tweet_id")
	@JoinColumn(name = "author")
	@Id
	@GeneratedValue
	private Long id;
	
	
}