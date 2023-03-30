package com.cooksys.assessment1Team3.repositories;

import com.cooksys.assessment1Team3.entities.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

}
