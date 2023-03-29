package com.cooksys.assessment1Team3.repositories;

import com.cooksys.assessment1Team3.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Tweet findAllByDeletedFalse();
}
