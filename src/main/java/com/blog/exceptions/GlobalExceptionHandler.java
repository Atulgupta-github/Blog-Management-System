package com.blog.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String msg= ex.getMessage();
		ApiResponse apiresponse = new ApiResponse(msg,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentnotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((er)->{
			String fieldName= ((FieldError) er).getField();
			String mesage= er.getDefaultMessage();
			resp.put(fieldName, mesage);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
