package com.videotube.service;

import java.util.List;

import com.videotube.entity.Category;

public interface CategoryService {
  public Category createCategory(Category category);
  public List<Category> getAllCategory(); 
  public Category getCategoryByName(String name);
  public Category deleteByName(String name);
  
}
