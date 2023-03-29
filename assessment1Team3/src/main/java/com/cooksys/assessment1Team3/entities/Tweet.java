package com.cooksys.assessment1Team3.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweets")
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User author;

    @CreationTimestamp
    private Timestamp posted = Timestamp.valueOf(LocalDateTime.now());

    private boolean deleted;

    private String content;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Tweet inReplyTo;

    @ManyToOne
    @JoinColumn(name = "repost_id")
    private Tweet repostOf;

    @ManyToMany
    @JoinTable
    private List<Hashtag> hashtags;

    @ManyToMany
    @JoinTable
    private List<User> likedUsers;

    @ManyToMany
    @JoinTable
    private List<User> mentionedUsers;

}
