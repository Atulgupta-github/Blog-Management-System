package com.blog.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.blog.service.implement.BlogServiceImp;





@Controller
public class BlogController {

    @Autowired
    private BlogServiceImp blogService;

    // Show the login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Process login request
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Here you should add actual authentication logic (check username, password)
        if ("admin".equals(username) && "admin123".equals(password)) {
            model.addAttribute("username", username);
            return "dashboard";
        }
        return "login";
    }

    // Show dashboard (after login)
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    // Show create blog page
    @GetMapping("/createBlog")
    public String showCreateBlog() {
        return "createBlog";
    }

    // Save new blog
    @PostMapping("/saveBlog")
    public String saveBlog(@RequestParam String title, @RequestParam String content) {
        blogService.saveBlog(title, content);
        return "redirect:/viewBlogs";
    }

    // Show all blogs
    @GetMapping("/viewBlogs")
    public String viewBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "viewBlogs";
    }

    // Show edit blog page
    @GetMapping("/editBlog")
    public String editBlog(@RequestParam Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id));
        return "editBlog";
    }

    // Update blog
    @PostMapping("/updateBlog")
    public String updateBlog(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        blogService.updateBlog(id, title, content);
        return "redirect:/viewBlogs";
    }

    // Delete blog
    @GetMapping("/deleteBlog")
    public String deleteBlog(@RequestParam Long id) {
        blogService.deleteBlog(id);
        return "redirect:/viewBlogs";
    }

    // Logout
    @PostMapping("/logout")
    public String logout() {
        // Invalidate session or add additional logout logic if needed
        return "login";
    }
}
