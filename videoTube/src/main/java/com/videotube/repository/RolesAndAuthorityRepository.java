package com.videotube.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videotube.entity.RolesAndAuthority;

public interface RolesAndAuthorityRepository extends JpaRepository<RolesAndAuthority, Long> {
   Optional<RolesAndAuthority> findByName(String name);
}
