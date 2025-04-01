package com.blog.service;

import com.blog.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto dto);
	
	PostDto updatePost(PostDto dto, Integer id);
	
	void DeletePost(Integer id);
	
	
}
