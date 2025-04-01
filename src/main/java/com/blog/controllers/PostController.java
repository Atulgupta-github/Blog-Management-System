package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;

}
