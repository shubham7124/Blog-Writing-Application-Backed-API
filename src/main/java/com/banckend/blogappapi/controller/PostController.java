package com.banckend.blogappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.entity.Post;
import com.banckend.blogappapi.payloads.ApiResponse;
import com.banckend.blogappapi.payloads.PostDto;
import com.banckend.blogappapi.payloads.PostResponse;
import com.banckend.blogappapi.services.PostService;

import lombok.Getter;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired  
	private PostService postServive;
	
//	create 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	 public ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto, 
			 @PathVariable Integer userId, 
			 @PathVariable Integer categoryId)
	 {
		PostDto createPostDto=this.postServive.createPost(postDto, userId, categoryId);
		 return new  ResponseEntity<PostDto>(createPostDto,HttpStatus.CREATED);
		 
	 }
	
//	get PostDto by User id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		System.out.println("Controller id "+userId);
		List<PostDto> postDto=this.postServive.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
//	get PostDto using category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
	
		List<PostDto> postsDto=this.postServive.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postsDto,HttpStatus.OK);
	}
	
//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue ="0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
			)
	{
		PostResponse allPost=this.postServive.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
// get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postDto=this.postServive.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
//	Delete the post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postServive.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Succesfully", true),HttpStatus.OK );
	}
// Update the Post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto post=this.postServive.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
// Searching method
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords)
	{
		List<PostDto> postDto=this.postServive.searchPost(keywords);
		
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}

}
