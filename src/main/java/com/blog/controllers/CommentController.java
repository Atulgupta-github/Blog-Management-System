package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiResponse;
import com.blog.dto.CommentDto;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comment")
	public ResponseEntity<CommentDto> createCommentDto(@RequestBody CommentDto commentDto , @PathVariable Integer postId){
		CommentDto dto = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment (@PathVariable Integer commentId){
		this.commentService.deleteComment (commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("delete succesfully",true),HttpStatus.OK);
	}

}
