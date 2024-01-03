package com.videotube.service;

import java.util.List;

import com.videotube.entity.User;

public interface UserService {
   public User addUser(User user);
   public User updateUser(User user);
   public List<User> viewAllUser();
   public User viewUserById(Long id);
   public User deleteUser(Long id);
   
}
