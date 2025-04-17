package com.blog.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entites.Blog;
import com.blog.service.BlogService;

@Service
public class BlogServiceImp{

	private List<Blog> blogs;

    public void saveBlog(String title, String content) {
        // Add logic to save blog
        blogs.add(new Blog(title, content));
    }

    public List<Blog> getAllBlogs() {
        return blogs;
    }

    public Blog getBlogById(Long id) {
        return blogs.stream().filter(blog -> blog.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateBlog(Long id, String title, String content) {
        Blog blog = getBlogById(id);
        if (blog != null) {
            blog.setTitle(title);
            blog.setContent(content);
        }
    }

    public void deleteBlog(Long id) {
        blogs.removeIf(blog -> blog.getId().equals(id));
    }
}
