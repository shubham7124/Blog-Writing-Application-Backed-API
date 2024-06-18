package com.banckend.blogappapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banckend.blogappapi.entity.Category;
import com.banckend.blogappapi.entity.Post;
import com.banckend.blogappapi.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    
    List<Post> findByTitleContaining(String title);
    
 
   

}
