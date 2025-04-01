package com.blog.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDto;
import com.blog.entites.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.UserRepo;
import com.blog.service.UserService;

@Service
public class UserserviceImp implements UserService {
	
	@Autowired
	private UserRepo userRep;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		
		User user = this.dtoToUser(userdto);
		User savedUser =this.userRep.save(user);
		
		// TODO Auto-generated method stub
		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto dto, Integer id) {
		
		
		User user = this.userRep.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setAbout(dto.getAbout());
		user.setPassword(dto.getPassword());
		
		User updatedUser = this.userRep.save(user);
		UserDto dto1=  UserToDto(updatedUser);
		
		return dto1;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRep.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
		UserDto dto1=  UserToDto(user);
		return dto1;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = this.userRep.findAll();
		
		List<UserDto> dtoList= userList.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void DeleteUser(Integer userId) {
		User user = this.userRep.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRep.delete(user);

	}
	
	
	private User dtoToUser(UserDto dto) {
	//	User user = new User();
		
		User user = this.modelMapper.map(dto,User.class);
		
		
		/*
		 * user.setUserId(dto.getId()); user.setName(dto.getName());
		 * user.setEmail(dto.getEmail()); user.setAbout(dto.getAbout());
		 * user.setPassword(dto.getPassword());
		 * 
		 */return user;
		
	}
	
	private  UserDto UserToDto (User user) {
		/*
		 * UserDto dto = new UserDto(); dto.setId(user.getUserId());
		 * dto.setName(user.getName()); dto.setEmail(user.getEmail());
		 * dto.setAbout(user.getAbout()); dto.setPassword(user.getPassword());
		 */
		
		UserDto dto= this.modelMapper.map(user, UserDto.class);
		return dto;
		
		
		
	}

}
