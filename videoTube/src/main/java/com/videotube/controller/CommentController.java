package com.videotube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videotube.entity.Comment;
import com.videotube.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
 @Autowired
 private CommentService cs;
 @PostMapping("/createcomment")
 public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
	 Comment cmt= cs.addComment(comment);
	 return new ResponseEntity<Comment>(cmt, HttpStatus.CREATED);
 }
 @GetMapping("/getAllComments")
 public ResponseEntity<List<Comment>> getList(){
	List<Comment> list=cs.getAllComment();
	return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
 }
 @PatchMapping("/updatecomment")
 public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
	 Comment cmt= cs.updateComment(comment);
	 return new ResponseEntity<Comment>(cmt, HttpStatus.ACCEPTED);
 }
 @DeleteMapping("/delete")
 public ResponseEntity<Comment> delete(@RequestParam("deleteId") long id){
	 Comment cmt= cs.deleteComment(id);
	 return new ResponseEntity<Comment>(cmt, HttpStatus.ACCEPTED);
 }
 
}
