package com.videotube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videotube.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{

}
