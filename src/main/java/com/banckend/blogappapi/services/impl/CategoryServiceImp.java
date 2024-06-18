package com.banckend.blogappapi.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.apache.bcel.classfile.ModuleMainClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banckend.blogappapi.entity.Category;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.CategoryDto;
import com.banckend.blogappapi.repositories.CategoryRepo;
import com.banckend.blogappapi.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelMapper.map(categoryDto,Category.class);
		Category addrdCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addrdCat, CategoryDto.class);
	}

	@Override
	public CategoryDto udateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id", categoryId));
		cat.setCategoryTittle(categoryDto.getCategoryTittle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		 Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new  ResourceNotFoundException("Category","Category ID", categoryId));
		  
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDto=categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto ;
	}

}
