package com.videotube.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videotube.entity.User;
import com.videotube.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository ur;
	

	public UserServiceImpl(UserRepository ur) {
		super();
		this.ur = ur;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return ur.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> u= ur.findById(user.getUserId());
		if(u.isPresent()) return ur.save(user);
		throw new NullPointerException("User not found");
	}

	@Override
	public List<User> viewAllUser() {
		if(ur.count()!=0) {
			return ur.findAll();
		}
		throw new NullPointerException("No user list found");
	}

	@Override
	public User viewUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<User> u=ur.findById(id);
		if(u.isPresent()) return u.get();
		 throw new NullPointerException("User Not found");
	}

	@Override
	public User deleteUser(Long id) {
		Optional<User> u=ur.findById(id);
		if(u.isPresent()) {
			ur.deleteById(id);
			return u.get();
		}
			
		 throw new NullPointerException("User Not found");
	}

}
