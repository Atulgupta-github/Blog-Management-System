package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.JwtAuthRequest;
import com.blog.dto.JwtAuthResponse;
import com.blog.dto.UserDto;
import com.blog.exceptions.ApiException;
import com.blog.security.JwtTokenHelper;
import com.blog.service.UserService;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserService userService;
	
	

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToke( 
			@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		
		
		 String token= this.jwtTokenHelper.generateToken(userDetails);
		 
		 JwtAuthResponse  response = new JwtAuthResponse();
		 response.setToken(token);
		 
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
			
		}catch (BadCredentialsException e) {
			System.out.println("Invalid credentials");
			throw new ApiException("Invalid credentials");
			
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registeUser(@RequestBody UserDto userDto){
		UserDto dto =this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
	}

}
