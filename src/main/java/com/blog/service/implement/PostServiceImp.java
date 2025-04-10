package com.blog.service.implement;


import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entites.Category;
import com.blog.entites.Post;
import com.blog.entites.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImp implements PostService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private UserRepo userRepo;
	@Override
	public PostDto createPost(PostDto dto, Integer catId, Integer userId) {
		Category category = this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", catId));
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "USERiD", userId));
		
		Post  post = this.PostDtoToPostEntity(dto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post savedPost=this.postRepo.save(post);
		return this.PostEntityToPostDto(savedPost);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "postId", id));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.PostEntityToPostDto(updatedPost);
	}

	
	
	
	@Override
	public void DeletePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "postId", id));
		this.postRepo.delete(post);

	}
	
	
	
	public PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy,String sortDir){
		Sort sort= null;
		if("asc".equalsIgnoreCase(sortDir)) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber,pageSize ,sort);
		Page<Post> postPage =  this.postRepo.findAll(p);
		List<Post>postList=postPage.getContent();
		List<PostDto> dtoList= postList.stream().map(e->PostEntityToPostDto(e)).collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setContent(dtoList);
		response.setPageNumber(postPage.getNumber());
		response.setPageSize(postPage.getSize());
		response.setTotalElements(postPage.getTotalElements());
		response.setTotalPages(postPage.getTotalPages());
		response.setLastPage(postPage.isLast());
		return response;
	}
	@Override
	public PostDto getpostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		return this.PostEntityToPostDto(post);
	}
	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		List<Post> postList= this.postRepo.findByCategory(category);
		List<PostDto> dtoList= postList.stream().map(e->PostEntityToPostDto(e)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		List<Post> postList= this.postRepo.getAllPostByUser(user);
		List<PostDto> dtoList= postList.stream().map(e->PostEntityToPostDto(e)).collect(Collectors.toList());
		return dtoList;
	}
	
	
	
	
	public PostDto PostEntityToPostDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
				
	}
	
	public Post PostDtoToPostEntity(PostDto dto) {
		Post post = this.modelMapper.map(dto, Post.class);
		return post;
	}

	@Override
	public List<PostDto> searchPost(String keword) {
		List<Post> post = this.postRepo.findByTitleContaining(keword);
		List<PostDto> dtoList = post.stream().map(e->this.PostEntityToPostDto(e)).collect(Collectors.toList());
		return dtoList;
	}

	/*
	 * @Override public List<PostDto> searchPostByContent(String keyword) {
	 * List<Post> post = this.postRepo.searchPostByContent("%"+keyword+"%");
	 * List<PostDto> dtoList =
	 * post.stream().map(e->this.PostEntityToPostDto(e)).collect(Collectors.toList()
	 * ); return dtoList; }
	 */



}
