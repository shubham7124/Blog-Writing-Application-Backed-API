package com.banckend.blogappapi.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.UserAllResponse;
import com.banckend.blogappapi.repositories.UserRepo;
import com.banckend.blogappapi.services.AdminServices;

@Service
public class AdminServiceImpl implements AdminServices {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	
	public UserAllResponse getUsrInfo(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
		return this.mapper.map(user, UserAllResponse.class);
	}
	
	
}
