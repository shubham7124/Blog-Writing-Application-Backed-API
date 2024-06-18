package com.banckend.blogappapi.services;

import java.util.List;

import com.banckend.blogappapi.entity.Comment;
import com.banckend.blogappapi.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
     CommentDto getCommentsByID(Integer commentId );
	
     List<CommentDto> getAllcomment();
}
