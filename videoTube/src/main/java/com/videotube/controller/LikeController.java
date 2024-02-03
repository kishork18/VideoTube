package com.videotube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videotube.entity.Like;
import com.videotube.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
	@Autowired
	private LikeService likeserv;
	@PostMapping("/addlike")
	public ResponseEntity<Like> addlike(Like like){
		Like addedLike= likeserv.addLike(like);
		return new ResponseEntity<Like>(addedLike, HttpStatus.CREATED);
		
	}
	@GetMapping("/getAllLike")
	public ResponseEntity<List<Like>> getAllLike(){
		List<Like> list= likeserv.allLikeList();
		return new ResponseEntity<List<Like>>(list, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/removelike")
	public ResponseEntity<Like> removeLike(long id){
		Like like=likeserv.removeLike(id);
		return new ResponseEntity<Like>(like, HttpStatus.OK);
	}

}
