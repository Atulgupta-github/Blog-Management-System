package com.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.dto.ApiResponse;
import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.service.FileServiceRemote;
import com.blog.service.PostService;





@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileServiceRemote fileServiceRemote;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto savedDto = this.postService.createPost(dto, categoryId, userId);
		
		return new ResponseEntity<PostDto>(savedDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId){
		List<PostDto> dtoList= this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK);
		
	}
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer catId ){
		List<PostDto> dtoList= this.postService.getPostByCategory(catId);
		return new ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue ="postId" ,required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir){
			PostResponse postResp= this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResp,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
		PostDto post= this.postService.getpostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.DeletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("delete succesfully", true), HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto dto){
		PostDto updatedPost = this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword){
		List<PostDto> dtoList = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/posts/searchByContent/{keyword}") public
	 * ResponseEntity<List<PostDto>> searchPostByContent(@PathVariable("keyword")
	 * String keyword){ List<PostDto> dtoList =
	 * this.postService.searchPostByContent(keyword); return new
	 * ResponseEntity<List<PostDto>>(dtoList,HttpStatus.OK); }
	 */
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity< PostDto> uploadImage(
			@RequestParam("Image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto= this.postService.getpostById(postId);
		
		
		String filename=	this.fileServiceRemote.uploadImage(path, image);
		postDto.setImageName(filename);
		PostDto updatePost=  this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping(value="/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException{
		InputStream resource = this.fileServiceRemote.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	

}
