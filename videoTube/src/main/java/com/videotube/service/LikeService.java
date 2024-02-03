package com.videotube.service;

import java.util.List;

import com.videotube.entity.Like;

public interface LikeService {
  public Like addLike(Like like);
  public Like removeLike(Long id);
  public List<Like> allLikeList();
}
