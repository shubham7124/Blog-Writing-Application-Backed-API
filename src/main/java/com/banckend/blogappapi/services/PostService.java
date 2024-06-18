package com.banckend.blogappapi.services;

import java.util.List;

import com.banckend.blogappapi.entity.Post;
import com.banckend.blogappapi.payloads.PostDto;
import com.banckend.blogappapi.payloads.PostResponse;
import com.banckend.blogappapi.repositories.PostRepo;

public interface PostService {
	
// create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
//delete
	void deletePost(Integer postId);
	
//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDic);
	
//get single Post
	PostDto getPostById(Integer postId);
	
//get all post by category 
	List<PostDto> getPostByCategory(Integer categoyId);

	
// get all post by user
	List<PostDto> getPostByUser(Integer useId);
	
//serach by keywords
	List<PostDto> searchPost(String keyword);	
	

}
