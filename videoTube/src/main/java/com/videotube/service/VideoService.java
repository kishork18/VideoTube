package com.videotube.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.videotube.entity.Video;

public interface VideoService {
  public Video uploadVideo(MultipartFile file,Video video);
  public Video updateVideoCont(Video video);
  public List<Video> getAllVideo();
  public Video getVideoById(Long id);
  public String deleteVideo(Long id);
  public List<Video> publicList(List<Video> list);
  public File convertMultiPartToFile(MultipartFile file) throws IOException ;
}
