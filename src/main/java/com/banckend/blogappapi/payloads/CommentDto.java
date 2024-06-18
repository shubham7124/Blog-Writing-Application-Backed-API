package com.banckend.blogappapi.payloads;

import com.banckend.blogappapi.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
	
	private int id;
	private String  comment;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
