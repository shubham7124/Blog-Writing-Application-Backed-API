package com.banckend.blogappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.payloads.ApiResponse;
import com.banckend.blogappapi.payloads.CategoryDto;
import com.banckend.blogappapi.services.CategoryService;
import com.banckend.blogappapi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
//Create
	
		@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategoryDto,HttpStatus.CREATED);
	}
//Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categryDtp, @PathVariable Integer catId)
	{
		CategoryDto upateCategoryDto=this.categoryService.udateCategory(categryDtp, catId);
		
		return new ResponseEntity<CategoryDto>(upateCategoryDto,HttpStatus.OK);
	}
//Delete
	@DeleteMapping("/{catId}")
	public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer categoryId)
	{
	      this.categoryService.deleteCategory(categoryId);
	      
	      return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully..",true),HttpStatus.OK);
	}
//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> gatCategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
//get all Category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategor()
	{
		List<CategoryDto> category=this.categoryService.getCategories();
		return new  ResponseEntity<List<CategoryDto>>(category,HttpStatus.OK);
	}
}
