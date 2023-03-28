package com.cooksys.assessment1Team3.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String label;

	@Column(nullable = false)
	@Setter(AccessLevel.NONE)
	private LocalDateTime firstUsed;

	@Column(nullable = false)
	private LocalDateTime lastUsed;

	@ManyToMany(mappedBy = "hashtags")
	private Set<Tweet> tweets;

}
