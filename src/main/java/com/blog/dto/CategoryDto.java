package com.blog.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
	
	private Integer categoryId;
	
	@Size(min=4, max = 200, message = "categoryTitle is must be greater then 4 char")
	private String categoryTitle;
	
	private String categoryDecription;

}
