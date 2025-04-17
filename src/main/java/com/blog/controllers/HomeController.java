package com.blog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.entites.Blog;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(new Blog("First Blog", "This is your first blog post!"));
        blogs.add(new Blog("Second Blog", "This is your second blog post!"));
        model.addAttribute("blogs", blogs);
        return "index";
    }
}
