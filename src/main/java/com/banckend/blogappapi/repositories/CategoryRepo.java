package com.banckend.blogappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banckend.blogappapi.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
