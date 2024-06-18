package com.banckend.blogappapi.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.banckend.blogappapi.exception.ApiException;

import com.banckend.blogappapi.payloads.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//geting token from header form the spacific name key:value

		String requestToken = request.getHeader("Authorization");

//token starting with--(Bearer)-> "Bearer sknandfornasdfoinan (0-6 and space 7)"
		System.out.println("Token--" + requestToken);
		String username = null;
		String token = null;

//Checking the token starting with Bearer 
		if (requestToken != null && requestToken.startsWith("Bearer")) {

//			get Actual token , remove bearer form token
			token = requestToken.substring(7);
		
			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);

			}
			catch (IllegalArgumentException e) {

				System.out.println("IllegalArgumentException--"+e.getMessage());
				 request.setAttribute("IllegalArgumentException", e.getMessage());
		       
			} 
			catch (ExpiredJwtException e) {
				System.out.println("ExpiredJwtException---"+e.getMessage());
	            request.setAttribute("ExpiredJwtException", e.getMessage());
	        }
			catch (MalformedJwtException e) {
				System.out.println("MalformedJwtException---"+e.getMessage());
				 request.setAttribute("MalformedJwtException", e.getMessage());
	            
			}

			catch (SignatureException e) {

				System.out.println("SignatureException---"+e.getMessage());
				 request.setAttribute("SignatureException", e.getMessage());
				
			}
			


		} else {

			System.out.println("JWT Token doese not begin  with 'Bearer'");

		}

//once the we get the token then startin th validated

//		user name chacking or security context authentication aslo be null
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			loaded the user detalis 
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//			validate the token with user
			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

//				now setting the authentication in security context holder
//				we need pass authentication obj to pass

				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);

			} else {
				System.out.println("Token not validate with the userDetail's");
			}

		} else {
			System.out.println("user name  null and context not null");

		}

		filterChain.doFilter(request, response);
	}

}
