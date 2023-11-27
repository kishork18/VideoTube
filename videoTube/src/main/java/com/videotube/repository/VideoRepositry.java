package com.videotube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videotube.models.Video;

public interface VideoRepositry extends JpaRepository<Video, Long> {

}
