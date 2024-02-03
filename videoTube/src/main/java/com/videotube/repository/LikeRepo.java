
package com.videotube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videotube.entity.Like;

public interface LikeRepo extends JpaRepository<Like, Long> {

}
