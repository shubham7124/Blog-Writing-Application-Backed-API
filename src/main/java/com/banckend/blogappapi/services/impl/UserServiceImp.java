package com.banckend.blogappapi.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banckend.blogappapi.entity.Role;
import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.exception.ApiException;
import com.banckend.blogappapi.exception.ExistException;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.PostDto;
import com.banckend.blogappapi.payloads.UserAllResponse;
import com.banckend.blogappapi.payloads.UserDto;
import com.banckend.blogappapi.repositories.AppConstant;
import com.banckend.blogappapi.repositories.RoleRepo;
import com.banckend.blogappapi.repositories.UserRepo;
import com.banckend.blogappapi.services.UserService;

import io.swagger.v3.oas.models.responses.ApiResponse;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createAdmin(UserDto userDto) {
		
		checkEmialExist(userDto.getEmail());
		// creaet a user ref and assigened value that converted into uerDto -----> User
		User user = this.dtoToUser(userDto);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		// save the user valu in database using the userRepo & its return a response
		// that stored in savedUser
//		adding the role
		Role role = this.roleRepo.findById(AppConstant.ROLE_ADMIN).get();
		user.getRole().add(role);
		User savedUser = this.userRepo.save(user);
		// return the converted user ----- to-- userDto
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		user.setAbout(userDto.getAbout());

		User upadateUser = this.userRepo.save(user);
		UserDto updateUserDto = this.userToUserDto(upadateUser);

		return updateUserDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

		return userToUserDto(user);
	}

	@Override
	public List<UserAllResponse> getAllUsers() {
		// TODO Auto-generated method stub

		List<User> users = this.userRepo.findAll();

		List<UserAllResponse> userAllResponses = users.stream().map((user)-> this.modelMapper.map(user, UserAllResponse.class)).collect(Collectors.toList());
		return userAllResponses;
	}

//	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

//		 user.setId(userDto.getId());
//		 user.setName(userDto.getName());
//		 user.setEmail(userDto.getEmail());
//		 user.setAbout(userDto.getAbout());
//		 user.setPassword(userDto.getPassword());

		return user;
	}

	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

//		 userDto.setId(user.getId());
//		 userDto.setName(user.getName());
//		 userDto.setEmail(user.getEmail());
//		 userDto.setPassword(user.getPassword());
//		 userDto.setAbout(user.getAbout());

		return userDto;
	}

//	Noemal person
	@Override
	public UserDto registerUser(UserDto userDto) {
		// TODO Auto-generated method stub
		checkEmialExist(userDto.getEmail());
		User user = this.modelMapper.map(userDto, User.class);
		
//		 1. passwprd
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//		ROLE
		Role role = this.roleRepo.findById(AppConstant.ROLE_NORMAL).get();

		user.getRole().add(role);
		User user2 = this.userRepo.save(user);
	
		return this.modelMapper.map(user2, UserDto.class);
	}
	

	private void checkEmialExist(String email) {
		// TODO Auto-generated method stub
		
		List<User> user=this.userRepo.findAllByEmail(email);
		if(user.isEmpty()) {

		}
		else {   
			System.out.println("exist");
			throw new ExistException("Already Ragister Email Id", false);
			
		}
	}

	private void passwordEncoder(String password) {
		// TODO Auto-generated method stub

	}

}
