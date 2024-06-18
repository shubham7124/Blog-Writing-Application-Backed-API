package com.banckend.blogappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.payloads.ApiResponse;
import com.banckend.blogappapi.payloads.UserAllResponse;
import com.banckend.blogappapi.payloads.UserDto;
import com.banckend.blogappapi.services.AdminServices;
import com.banckend.blogappapi.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
   @Autowired
   private UserService userService;
  @Autowired
  private AdminServices adminServices;
//	Register New Admin
   
	 @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register/admin")
	public ResponseEntity<UserDto> registerAdmin(@RequestBody UserDto uerDto)
	{
		UserDto registerUser = this.userService.createAdmin(uerDto);
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
	 

//	delete by admin only
//	 Delete the user
    @DeleteMapping("/delete/{userId}")
 	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		System.out.println("Indside the Delete");
		this.userService.deleteUser(uid);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
    
//GET ALL USER
//	 GET user
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/AllUser")
	public ResponseEntity<List<UserAllResponse>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

    
//GET USER BY ID	
	//Get Single user
		@PreAuthorize("hasRole('ADMIN')")
		@GetMapping("/{userId}")
		public ResponseEntity<UserAllResponse> getSingleUser(@PathVariable Integer userId) {
			return ResponseEntity.ok(this.adminServices.getUsrInfo(userId));
		}
}
