package com.blog.service;

import java.util.List;

import com.blog.dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto dto);
	
	CategoryDto updateCategory(CategoryDto dto,Integer id);
	
	void deleteCategory(Integer id);
	
	CategoryDto getCategoryById(Integer id);
	
	List<CategoryDto> getAllCategories();
}
