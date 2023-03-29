package com.cooksys.assessment1Team3.entities;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ManyToMany;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	@CreationTimestamp
	private Timestamp joined = Timestamp.valueOf(LocalDateTime.now());
	private boolean deleted;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	@ManyToMany(mappedBy = "mentionedUsers")
	private List<Tweet> mentions;
	@ManyToMany(mappedBy = "likes")
	private List<Tweet> likes;

}
