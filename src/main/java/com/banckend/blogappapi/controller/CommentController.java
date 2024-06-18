package com.banckend.blogappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.entity.Comment;
import com.banckend.blogappapi.payloads.ApiResponse;
import com.banckend.blogappapi.payloads.CommentDto;
import com.banckend.blogappapi.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId)
	{
		CommentDto createdComment=this.commentService.createComment(comment, postId);
	  return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
	}   
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deletComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		
		return  new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully..!", true), HttpStatus.OK);
	}
	
//	Get  Comment  by id
	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDto> getAllComments(@PathVariable Integer commentId)
	{
		CommentDto commentDtos=this.commentService.getCommentsByID(commentId);
		return new ResponseEntity<CommentDto>(commentDtos, HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<CommentDto>>getAllComments()
	{
		List<CommentDto> comments=this.commentService.getAllcomment();
		return new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK);
	}
}
