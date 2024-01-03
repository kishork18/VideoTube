package com.videotube.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.videotube.entity.User;
import com.videotube.entity.Video;
import com.videotube.entity.Visibility;
import com.videotube.repository.VideoRepositry;
@Service
public class VideoServiceImpl implements VideoService {
   private VideoRepositry videoRepo;
   private final Cloudinary cloud;
   private UserService us;
	
	public VideoServiceImpl(VideoRepositry videoRepo, Cloudinary cloud, UserService us) {
	super();
	this.videoRepo = videoRepo;
	this.cloud = cloud;
	this.us = us;
}

	@Override
	public Video uploadVideo(MultipartFile file,Video video) {
		 User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		try {
			Map<String, Object> uploadMap = cloud.uploader().upload(file.getBytes(),  ObjectUtils.asMap("resource_type", "video",
				    "public_id", "videotube/"+video.getTitle(),
				    "is_audio", true
				    /*"eager", Arrays.asList(
				        new EagerTransformation().width(300).height(300).crop("pad").audioCodec("none"),
				        new EagerTransformation().width(160).height(100).crop("crop").gravity("south").audioCodec("true")),
				    "eager_async", true,
				    "eager_notification_url", "http://localhost:8080/videos/upload-video"*/));
			video.setCategories(new ArrayList<>());
			video.setComments(new ArrayList<>());
			video.setLikes(new ArrayList<>());
			video.setLink(uploadMap.get("secure_url").toString());
			video.setUploadDate(LocalDate.now());
			video.setWatchHistory(new ArrayList<>());
			video.setUser(user);
			return videoRepo.save(video);
			       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
	}

	@Override
	public Video updateVideoCont(Video video) {
		Optional<Video> v= videoRepo.findById(video.getVideoId());
		if(v.isPresent()) return videoRepo.save(video);
		throw new NullPointerException("Video is not present with provided Id");
	}

	

	@Override
	public List<Video> getAllVideo() {
		// TODO Auto-generated method stub
		return videoRepo.findAll();
	}

	@Override
	public Video getVideoById(Long id) {
		Optional<Video> vid= videoRepo.findById(id);
		if(vid.isPresent()) return vid.get();
		throw new NullPointerException("Video is not present with given id");
	}

	@Override
	public String deleteVideo(Long id) {
		// TODO Auto-generated method stub
		Optional<Video> vid= videoRepo.findById(id);
		if(vid.isPresent()) {
			videoRepo.delete(vid.get());
			return "Video Deleted Successfully";
		} 
			
		throw new NullPointerException("Video is not present with given id");
	}

	@Override
	public List<Video> publicList(List<Video> list) {
		// TODO Auto-generated method stub
		return list.stream().filter(v->v.getVisibility()==Visibility.PUBLIC).toList();
	}
	@Override
	public File convertMultiPartToFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		    File convFile = new File(file.getOriginalFilename());
		    FileOutputStream fos = new FileOutputStream(convFile);
		    fos.write(file.getBytes());
		    fos.close();
		    return convFile;
		
	}

}
