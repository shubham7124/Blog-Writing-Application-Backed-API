package com.banckend.blogappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banckend.blogappapi.entity.Comment;

public interface CommentRespo extends JpaRepository<Comment, Integer>{

}
