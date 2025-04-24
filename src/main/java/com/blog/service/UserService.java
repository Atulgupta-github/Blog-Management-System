package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);
	
	UserDto createUser(UserDto userdto);
	
	UserDto updateUser(UserDto userdto,Integer id);
	
	UserDto getUserById( Integer id);
	
	List<UserDto> getAllUsers();
	
	void DeleteUser (Integer userId);
	
	
	
}
