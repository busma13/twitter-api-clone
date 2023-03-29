package com.cooksys.assessment1Team3.entities;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
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
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany(mappedBy = "likes")
	private Set<Tweet> likedTweets;
	
	@ManyToMany(mappedBy = "mentionedUsers")
	private Set<Tweet> mentionedTweets;
	
	@ManyToMany
	@JoinTable
	private Set<User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private Set<User> following;
	
}