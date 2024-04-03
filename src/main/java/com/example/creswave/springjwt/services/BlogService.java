package com.example.creswave.springjwt.services;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Blog createBlog (Blog blog){
        blog.setId(blog.getId());
        blog.setDescription(blog.getDescription());
        return blogRepository.save(blog);
    }

    public List<Blog> viewBlogs(){
        return blogRepository.findAll();
    }

    public Blog updateBlog(Long blogId, Blog blog){
        Blog blog1 = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("this blog does not exist"));
        blog1.setDescription(blog.getDescription());
        return blogRepository.save(blog1);
    }

    public void deleteBlog (Long blogId){
        Blog blog1 = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("this blog does not exist"));
        blogRepository.delete(blog1);
    }
}
