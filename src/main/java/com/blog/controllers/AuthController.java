package com.blog.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
	        String email = credentials.get("email");
	        String password = credentials.get("password");

	        UserDto user = userService.getUserByEmail(email); // Tu bana le method agar nahi hai
	        if (user != null && user.getPassword().equals(password)) {
	            return ResponseEntity.ok(user); // Password hash match bhi tu add kar sakta hai
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }
}
