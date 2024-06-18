package com.banckend.blogappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banckend.blogappapi.exception.ApiException;
import com.banckend.blogappapi.exception.GlobalExceptionHandler;
import com.banckend.blogappapi.exception.ResourceNotFoundException;
import com.banckend.blogappapi.payloads.JwtAuthRequest;
import com.banckend.blogappapi.payloads.JwtAuthResponse;
import com.banckend.blogappapi.payloads.UserDto;
import com.banckend.blogappapi.security.JwtTokenHelper;
import com.banckend.blogappapi.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		System.out.println("Username "+request.getEmail());
		System.out.println("Password"+request.getPassword());
		
		this.authenticate(request.getEmail(),request.getPassword());
		UserDetails loadUserByUsername = this.userDetailsService.loadUserByUsername(request.getEmail());
		String generateToken = this.jwtTokenHelper.generateToken(loadUserByUsername);
	    System.out.println("gentated Token--"+generateToken);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(generateToken);
		
		
  		return new  ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
  		
	}
	
	private void authenticate(String username, String password)throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=new  UsernamePasswordAuthenticationToken(username, password);
		
		try
		{
			
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e) {
			// TODO: handle exception
		
			System.out.println("Invalid Details");
			throw new ApiException(" Wrong Password");
		}		
	}
	
//	Register New Admin
//	 @PreAuthorize("hasRole('ADMIN')")
//	@PostMapping("/register/admin")
//	public ResponseEntity<UserDto> registerAdmin(@RequestBody UserDto uerDto)
//	{
//		UserDto registerUser = this.userService.createAdmin(uerDto);
//		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
//	}

}
