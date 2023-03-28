package com.cooksys.assessment1Team3.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<User> likes;

    @ManyToMany
    @JoinTable
    private List<User> mentionedUsers;

}