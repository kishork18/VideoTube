package com.videotube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videotube.entity.Category;
import com.videotube.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
 @Autowired
 private CategoryService catService;
 @PostMapping("/create")
 public ResponseEntity<Category> createCategory(@RequestBody Category category){
	 Category cat=catService.createCategory(category);
	 return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
 }
 @GetMapping("/getcategoris")
 public ResponseEntity<List<Category>> getAllCategory(){
	List<Category> list= catService.getAllCategory();
	return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
 }
 @GetMapping("/getcategoryByname")
 public ResponseEntity<Category> getCategoryByName(@RequestParam ("name") String name){
	 Category cat= catService.getCategoryByName(name);
	 return new ResponseEntity<Category>(cat, HttpStatus.OK);
 }
 @DeleteMapping("/deleteCat")
 public ResponseEntity<Category> deletecat(@RequestParam ("name") String name){
	 Category cat= catService.deleteByName(name);
	 return new ResponseEntity<Category>(cat, HttpStatus.OK);
 }
}
