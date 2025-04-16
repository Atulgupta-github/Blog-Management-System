package com.blog.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CommentDto;
import com.blog.entites.Comment;
import com.blog.entites.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.service.CommentService;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		CommentDto dto = this.modelMapper.map(savedComment, CommentDto.class); 
		return dto;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commetId", commentId));
		this.commentRepo.delete(comment);
		
	}

}
