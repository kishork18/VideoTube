package com.videotube.service;

import java.util.List;

import com.videotube.entity.Comment;

public interface CommentService {
 public Comment addComment(Comment comment);
 public List<Comment> getAllComment();
 public Comment updateComment(Comment comment);
 public Comment deleteComment(Long id);
}
