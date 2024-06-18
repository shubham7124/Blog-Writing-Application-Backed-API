package com.banckend.blogappapi.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.banckend.blogappapi.entity.Category;
import com.banckend.blogappapi.entity.Post;
import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.CategoryDto;
import com.banckend.blogappapi.payloads.PostDto;
import com.banckend.blogappapi.payloads.PostResponse;
import com.banckend.blogappapi.payloads.UserDto;
import com.banckend.blogappapi.repositories.CategoryRepo;
import com.banckend.blogappapi.repositories.PostRepo;
import com.banckend.blogappapi.repositories.UserRepo;
import com.banckend.blogappapi.services.PostService;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date(System.currentTimeMillis()));
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post savedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		 Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		 this.postRepo.delete(post);
	

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p=PageRequest.of(pageNumber,pageSize, sort);
		
		Page<Post> pagePost= this.postRepo.findAll(p);
		List<Post> allPosts=pagePost.getContent();
        
//		converted the Post into the PostDto
	    List<PostDto> postsDto=allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	    PostResponse postResponse=new PostResponse();
	    
	    postResponse.setContent(postsDto);
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    postResponse.setTotalPage(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
	    postResponse.setFirstPage(pagePost.isFirst());
	    
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoyId) {
		// TODO Auto-generated method stub
//		Feactch category using categoryId
		Category cat = this.categoryRepo.findById(categoyId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoyId));
//		post fetch using the category id
		List<Post> posts = this.postRepo.findByCategory(cat);
//		converting Post into the PostDto
		List<PostDto> potsDto = posts.stream().map((post) -> this.modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
		

		return potsDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer useId) {
		// TODO Auto-generated method stub
//	    Fetch the all user using userId
		System.out.println("User id-"+useId);
		User user=this.userRepo.findById(useId).orElseThrow(()-> new ResourceNotFoundException("User","User id", useId));
//	    Fetch Post using the user
	    List<Post> posts=this.postRepo.findByUser(user);
//	    Converting  the Post into PostDto
	    List<PostDto> postsDto= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
		return postsDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postsDto=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postsDto;
	}

	

}
