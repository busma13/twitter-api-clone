package com.cooksys.assessment1Team3.entities;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	// Embedded Credentials and Profile
	@Embedded
//	@AttributeOverrides({
//			@AttributeOverride(name = "username", column = @Column(name = "username")),
//			@AttributeOverride(name = "password", column = @Column(name = "password")),
//	})
	private Credentials credentials;
	
	@Embedded
	private Profile profile;
	
	private boolean deleted=false;
	
	@CreationTimestamp
	private Timestamp joined;
	
	@OneToMany(mappedBy="author")
	private List<Tweet> tweets;
	
	@ManyToMany
	@JoinTable(
    		name="user_likes",
    		joinColumns=@JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="tweet_id")
    		)
	private List<Tweet> likedTweets;
	
	@ManyToMany(mappedBy = "mentionedUsers")
	private List<Tweet> mentionedTweets;
	
	@ManyToMany
	@JoinTable(name="followers_following")
	private List<User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> following;
	
}
