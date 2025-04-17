package com.blog.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	
	
	private int userId;
	
	@NotEmpty
	@Size(min =4, message = "username must be min of 4 character")
	private String name;
	
	@Email(message = "Please enter valid email")
	private String email;
	
	@NotEmpty(message = "password can not be null")
	@Size(min =4, max=10, message = "password must be  4 to 10 character")
	private String password;
	
	@NotEmpty
	private String about;
	
}
