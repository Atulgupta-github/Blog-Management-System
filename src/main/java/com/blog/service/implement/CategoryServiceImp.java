package com.blog.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entites.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category cat = this.catDtoToCatEntity(dto);
		
		Category savedCat= this.catRepo.save(cat);
		
		CategoryDto newDto = this.catEntityToDto(savedCat);
		
		return newDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto,Integer id) {
		Category cat = this.catRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id not found", id));
		cat.setCategoryDecription(dto.getCategoryDecription());
		cat.setCategoryTitle(dto.getCategoryTitle());
		Category updatedCat = this.catRepo.save(cat);
		return this.catEntityToDto(updatedCat);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category cat = this.catRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "id not found", id));
		this.catRepo.delete(cat);

	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> catList = this.catRepo.findAll();
		List<CategoryDto> dtoList= catList.stream().map(e->catEntityToDto(e)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category cat = this.catRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","not found ", id));
		return this.catEntityToDto(cat);
	}
	
	
	
	public Category catDtoToCatEntity(CategoryDto dto) {
		Category cat = this.mapper.map(dto, Category.class);
		return cat;
		
	}
	
	public CategoryDto catEntityToDto(Category cat) {
		CategoryDto dto = this.mapper.map(cat, CategoryDto.class);
		return dto;
	}

}
