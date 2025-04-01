package com.blog.repositories;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entites.Post;
import com.blog.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	List<Post> getAllPostByUser(User user);
	List<Post> findByCategory(Category category);
}
