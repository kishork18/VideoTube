package com.videotube.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
 private final String FOLDER_PATH="C:\\Users\\DISHA COMPUTERS\\Desktop\\Desktop\\videoData";
 public String uploadVideo(MultipartFile file) throws IOException {
	  String filePath=FOLDER_PATH+file.getOriginalFilename();
	Video vid= videoRepo.save(Video.builder().title(file.getOriginalFilename())
			 .description("").uploadDate(LocalDate.now())
			 .type(file.getContentType()). videoPath(filePath)
			 .user(new User()).comments(new ArrayList<>()).likes(new ArrayList<>())
			 .categories(new ArrayList<>()).watchHistory(new ArrayList<>()).build());
	file.transferTo(new File(filePath));
	if(vid!=null) {
		return "video Uploaded Successfully";
	}
	 return null;
 }
 public byte [] downloadVideo(Long videoId) throws IOException {
	 Optional<Video> vid=videoRepo.findById(videoId);
	 String videoPath=vid.get().getVideoPath();
	 byte[] video= Files.readAllBytes(new File(videoPath).toPath());
	 return video;
 }
}
