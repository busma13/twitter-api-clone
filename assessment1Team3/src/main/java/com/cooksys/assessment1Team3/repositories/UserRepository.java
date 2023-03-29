package com.cooksys.assessment1Team3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.assessment1Team3.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // TODO: Do you need any derived queries? If so add them here.

}

