package com.example.creswave.springjwt.services;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Blog createBlog (Blog blog){
        blog.setId(blog.getId());
        blog.setDescription(blog.getDescription());
        return blogRepository.save(blog);
    }

    public Map<String, Object> viewBlogs(int page, int size){
        Map<String, Object> response = new HashMap<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> blogPage = blogRepository.findAll(pageable);

        List<Blog> blogs = blogPage.getContent();

        response.put("blogs", blogs);
        response.put("currentPage", blogPage.getNumber());
        response.put("totalItems", blogPage.getTotalElements());
        response.put("totalPages", blogPage.getTotalPages());

        return response;
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
