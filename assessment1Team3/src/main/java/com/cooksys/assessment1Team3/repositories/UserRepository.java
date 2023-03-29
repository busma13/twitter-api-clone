package com.cooksys.assessment1Team3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.assessment1Team3.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByDeletedFalse();

}

