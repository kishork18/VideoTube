package com.videotube.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videotube.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(String username);
}
