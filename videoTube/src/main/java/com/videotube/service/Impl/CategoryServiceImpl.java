package com.videotube.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videotube.entity.Category;
import com.videotube.repository.CategoryRepository;
import com.videotube.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public Category createCategory(Category category) {
		if(category!=null) {
			category.setVideos(new ArrayList<>());
			return catRepo.save(category);
			
		}
		throw new NullPointerException("Category name is null");
	}

	@Override
	public List<Category> getAllCategory() {
		return catRepo.findAll();
	}

	@Override
	public Category getCategoryByName(String name) {
		if(name!=null) {
			Category cat= catRepo.findByCategoryName(name).get();
			return cat;
		}
		throw new NullPointerException("Category name is null");
	}

	@Override
	public Category deleteByName(String name) {
		Category cat= getCategoryByName(name);
		if(cat!=null) {
			catRepo.delete(cat);
			return cat;
		}
		throw new NullPointerException("Category name is null");
	}

}
