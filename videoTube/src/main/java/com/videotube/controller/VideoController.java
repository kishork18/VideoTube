package com.videotube.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videotube.service.VideoService;

@RestController
public class VideoController {
  @Autowired
  private VideoService vc;
  @PostMapping("/upload-video")
  public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile file ) throws IOException{
	 String res= vc.uploadVideo(file);
	  return ResponseEntity.status(HttpStatus.OK).body(res);
 }
  @GetMapping("/fetch-videos/{id}")
  public ResponseEntity<?> downloadVideo(@PathVariable Long id) throws IOException{
	  byte[] videoData= vc.downloadVideo(id);
	  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("video/mp4")).body(videoData);
  }
}
