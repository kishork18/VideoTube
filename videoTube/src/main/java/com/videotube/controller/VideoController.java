package com.videotube.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.videotube.entity.Video;
import com.videotube.service.VideoService;


@RestController
@RequestMapping("/videos")

@CrossOrigin(origins = "*")
public class VideoController {
    private final VideoService vs;
   

    public VideoController(VideoService vs) {
        this.vs = vs;
        
    }

    @PostMapping("/upload-video")
    public ResponseEntity<Video> uploadVideo(
            @RequestParam("file") MultipartFile file, @RequestPart("video") Video video) throws IOException {
        
          
            
          
           Video vid= vs.uploadVideo(file, video);
          
            return new ResponseEntity<>(vid, HttpStatus.CREATED);
             
            
    }
    @GetMapping("/allVideos")
    public ResponseEntity<List<Video>> getAllVideos(){
    	List<Video> videoList= vs.getAllVideo();
    	return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
    }
}

