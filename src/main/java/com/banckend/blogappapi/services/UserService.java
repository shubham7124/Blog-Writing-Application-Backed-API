package com.banckend.blogappapi.services;

import java.util.List;

import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.payloads.UserAllResponse;
import com.banckend.blogappapi.payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	
	UserDto createAdmin(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserAllResponse> getAllUsers();
	
	void deleteUser(Integer userId);
}
