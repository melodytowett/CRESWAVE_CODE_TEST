package com.example.creswave.springjwt.controllers;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Blog>createBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }
    @GetMapping("/read")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Blog> readBlogs(){
        return blogService.viewBlogs();
    }
    @PutMapping("/updating/{blogId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Blog>updateBlog(@PathVariable Long blogId,@RequestBody Blog blog){
        return new ResponseEntity<>(blogService.updateBlog(blogId, blog),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{blogId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>deleteBlog(@PathVariable Long blogId){
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>("blog deleted Successfully",HttpStatus.OK);
    }
}
