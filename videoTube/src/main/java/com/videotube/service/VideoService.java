package com.videotube.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.videotube.models.User;
import com.videotube.models.Video;
import com.videotube.repository.VideoRepositry;
import com.videotube.util.VideoUtils;


@Service
public class VideoService {
 @Autowired
 private VideoRepositry videoRepo;
 
 public String uploadVideo(MultipartFile file) throws IOException {
	Video vid= videoRepo.save(Video.builder().title(file.getOriginalFilename())
			 .description("").uploadDate(LocalDate.now())
			 .type(file.getContentType()). videodata(VideoUtils.compress(file.getBytes()))
			 .user(new User()).comments(new ArrayList<>()).likes(new ArrayList<>())
			 .categories(new ArrayList<>()).watchHistory(new ArrayList<>()).build());
	if(vid!=null) {
		return "video Uploaded Successfully";
	}
	 return null;
 }
 public byte [] downloadVideo(Long videoId) {
	 Optional<Video> vid=videoRepo.findById(videoId);
	 return VideoUtils.decompress(vid.get().getVideodata());
 }
}
