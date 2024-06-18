package com.banckend.blogappapi.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {

	private String Email;
	private String password;
	
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
