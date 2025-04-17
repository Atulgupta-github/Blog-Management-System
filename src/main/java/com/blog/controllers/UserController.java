package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiResponse;
import com.blog.dto.UserDto;
import com.blog.service.UserService;





@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto){
		UserDto crtDto = this.userService.createUser(dto);
		
		return new ResponseEntity<>(crtDto , HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto dto, @PathVariable("userId") Integer id){
		UserDto updatedUser= this.userService.updateUser(dto, id);
		
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer id){
		this.userService.DeleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> list = this.userService.getAllUsers();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer id){
		UserDto user = this.userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	
	

}
