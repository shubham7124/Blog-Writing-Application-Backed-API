package com.banckend.blogappapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.CommentDto;
import com.banckend.blogappapi.payloads.PostResponse;
import com.banckend.blogappapi.repositories.CommentRespo;
import com.banckend.blogappapi.repositories.PostRepo;
import com.banckend.blogappapi.services.CommentService;
import com.banckend.blogappapi.entity.*;

@Service
public class CommentServicesImp implements CommentService {

	@Autowired
	private PostRepo postRespo;
	@Autowired
	private CommentRespo commentRespo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post =this.postRespo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRespo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment=this.commentRespo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id", commentId));
		this.commentRespo.delete(comment);
	}

	@Override
	public CommentDto getCommentsByID(Integer CommentId) {
		Comment comment=this.commentRespo.findById(CommentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id", CommentId));
//	return a Comment type  data, we need to convert into CommentDto type -->use the  modelMapper
		
		CommentDto commentDto=this.modelMapper.map(comment, CommentDto.class);
		
		return commentDto;
	}

	@Override
	public  List<CommentDto> getAllcomment() {
		// TODO Auto-generated method stub
		
		List<Comment> comments=this.commentRespo.findAll();
		List<CommentDto> commentDto=comments.stream().map((comment)-> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDto;
	}

}
