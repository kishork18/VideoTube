package com.videotube.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.videotube.entity.Like;
import com.videotube.entity.User;
import com.videotube.repository.LikeRepo;
import com.videotube.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
    private LikeRepo likerepo;
	@Override
	public Like addLike(Like like) {
		// TODO Auto-generated method stub
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		like.setLikeDate(LocalDate.now());
		like.setUser(user);
		return likerepo.save(like);
	}

	@Override
	public Like removeLike(Long id) {
		Like like=likerepo.findById(id).get();
		likerepo.delete(like);
		return like;
	}

	@Override
	public List<Like> allLikeList() {
		// TODO Auto-generated method stub
		List<Like> list= likerepo.findAll();
		if(list.size()!=0) {
			return list;
		}
		throw new NullPointerException("No Like found in List");
	}

}
