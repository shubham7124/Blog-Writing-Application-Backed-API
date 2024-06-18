package com.banckend.blogappapi.repositories;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banckend.blogappapi.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	Optional<User> findByEmail(String email);
	List<User> findAllByEmail(String email);
}
