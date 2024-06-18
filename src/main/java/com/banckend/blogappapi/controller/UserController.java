package com.banckend.blogappapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.entity.User;
import com.banckend.blogappapi.exception.ApiException;
import com.banckend.blogappapi.payloads.ApiResponse;
import com.banckend.blogappapi.payloads.UserAllResponse;
import com.banckend.blogappapi.payloads.UserDto;
import com.banckend.blogappapi.repositories.UserRepo;
import com.banckend.blogappapi.services.UserService;
import com.banckend.blogappapi.services.impl.UserServiceImp;

import jakarta.validation.Valid;
import lombok.Getter;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private UserService userService;

//	Only added the admin as admin

	@PostMapping("/normaluser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto,HttpStatus.CREATED);
		
	
	}




//	put --udate the user
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> upateUser(@Valid @RequestBody UserDto userDo, @PathVariable("userId") Integer uid) {
		UserDto updateUser = this.userService.updateUser(userDo, uid);
		return ResponseEntity.ok(updateUser);
	}

//	 Admin
//	delete by admin only
//	 Delete the user
    @DeleteMapping("/delete/{userId}")
 	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		System.out.println("Indside the Delete");
		this.userService.deleteUser(uid);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

//	 GET user
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<UserAllResponse>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

//Get Single user
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
