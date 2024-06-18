package com.banckend.blogappapi.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserAllResponse {

	private int id;
	
    @NotEmpty
    @Size(min = 4,message ="Username must be min 4 characters" )
	private String name;
	
    @Email(message = "Email address is not valid")
	private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message ="Password must be min 3 and max 10 characters" )
	private String password;
	
	@NotEmpty
	private String about;
     

     private Set<RoleDto> roles=new HashSet<>();

	
	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}


	public int getId() {
		return id;
	}

    
	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

    
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

	
	
}