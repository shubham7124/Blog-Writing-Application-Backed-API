package com.banckend.blogappapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.exception.ApiException;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		Loading user form database by username mean email
//		 System.out.println("starting");
//		 User user =this.userRepo.findById(1).orElseThrow(()-> new ResourceNotFoundException(username, "id",1)); 
//		  System.out.println("the user list "+user.getName());
		
		  User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ApiException("Email Id Not Found"));
		
		return user;
	}

}
