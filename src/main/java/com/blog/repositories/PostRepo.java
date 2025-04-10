package com.blog.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entites.Category;
import com.blog.entites.Post;
import com.blog.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	List<Post> getAllPostByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
	/*
	 * @Query("select p from post p where p.content like :key") List<Post>
	 * searchPostByContent(@Param("key") String content);
	 */
}
