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
import com.blog.dto.CategoryDto;
import com.blog.service.CategoryService;



@RestController
@RequestMapping("api/category")
public class CategoryController {
	@Autowired
	private CategoryService catService;
	
	//CreateCategory
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto){
		CategoryDto savedDto = catService.createCategory(dto);
		return new  ResponseEntity<>(savedDto,HttpStatus.CREATED);
		
	}
	
	
	//updateCateGory
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable("catId") Integer id){
		CategoryDto updatedDto= this.catService.updateCategory(dto, id);
		return new ResponseEntity<CategoryDto>(updatedDto,HttpStatus.OK);
	}
	
	
	//deleteCategory
	
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("catId") Integer id){
		this.catService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted success fully",true),HttpStatus.OK);
	}
	
	
	//getALLcategory
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> catList= this.catService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(catList, HttpStatus.OK);
	}
	
	//getCategoryById
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") Integer id){
		CategoryDto dto = this.catService.getCategoryById(id);
		
		return ResponseEntity.ok(dto);
	}

}
