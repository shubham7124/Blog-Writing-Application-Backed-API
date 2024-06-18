package com.banckend.blogappapi.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEnrtyPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub

		// Prepare JSON error response
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
		if (request.getAttribute("expired") != null) {
			errorResponse.put("error", HttpStatus.UNAUTHORIZED);
			errorResponse.put("message", String.valueOf(request.getAttribute("expired")));
		}
		
		else if (request.getAttribute("SignatureException") != null) {
			errorResponse.put("error", HttpStatus.UNAUTHORIZED);
			errorResponse.put("message", String.valueOf(request.getAttribute("SignatureException")));
		}
		else if (request.getAttribute("MalformedJwtException") != null) {
			errorResponse.put("error", HttpStatus.UNAUTHORIZED);
			errorResponse.put("message", String.valueOf(request.getAttribute("MalformedJwtException")));
		}
		else if (request.getAttribute("IllegalArgumentException") != null) {
			errorResponse.put("error", HttpStatus.UNAUTHORIZED);
			errorResponse.put("message", String.valueOf(request.getAttribute("IllegalArgumentException")));
		}
		else {

			errorResponse.put("error", HttpStatus.UNAUTHORIZED);
			errorResponse.put("message", "Access Denied");
		}
		// Write JSON error response to the response body
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getWriter(), errorResponse);

	}

}
