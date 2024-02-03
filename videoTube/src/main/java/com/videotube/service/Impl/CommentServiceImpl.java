package com.videotube.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.videotube.entity.Comment;
import com.videotube.entity.User;
import com.videotube.repository.CommentRepo;
import com.videotube.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo cRepo;

	@Override
	public Comment addComment(Comment comment) {
		 User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		comment.setCommentDate(LocalDate.now());
		comment.setUser(user);
		return cRepo.save(comment);
	}

	@Override
	public List<Comment> getAllComment() {
		List<Comment> commentList=cRepo.findAll();
		return commentList;
	}

	@Override
	public Comment updateComment(Comment comment) {
	   Comment c= cRepo.findById(comment.getCommentId()).get();
	   c.setCommentText(comment.getCommentText());
	   
		return cRepo.save(c);
	}

	@Override
	public Comment deleteComment(Long id) {
		 Comment c= cRepo.findById(id).get();
		 cRepo.delete(c);
		return c;
	}

}
