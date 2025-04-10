package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entites.Post;

public interface PostService {

	PostDto createPost(PostDto dto, Integer catId, Integer userId);
	
	PostDto updatePost(PostDto dto, Integer id);
	
	void DeletePost(Integer id);
	
	PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String sortDir);
	
	PostDto getpostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	
	List<PostDto> searchPost(String keword);
	//List<PostDto> searchPostByContent(String keyword);
	
	
	
}
