package com.banckend.blogappapi.exception;

import java.net.http.HttpHeaders;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.banckend.blogappapi.payloads.ApiResponse;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
	{
	     String msg=ex.getMessage();
	     System.out.println(msg);
	     ApiResponse apiResponce=new ApiResponse(msg, false);
	     
		return new ResponseEntity<ApiResponse>(apiResponce,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
		    String message=error.getDefaultMessage();
		    resp.put(fieldName, message);
			
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ApiException ex)
	{
	     String msg=ex.getMessage();
	     System.out.println("Error Message "+msg);
	     ApiResponse apiResponce=new ApiResponse(msg, false);
		return new ResponseEntity<ApiResponse>(apiResponce,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExistException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ExistException ex)
	{
	     String msg=ex.getMs();
	     System.out.println("Error Message "+msg);
	     ApiResponse apiResponce=new ApiResponse(msg, false);
		return new ResponseEntity<ApiResponse>(apiResponce,HttpStatus.BAD_REQUEST);
	}
		
	
	
}
