package com.example.creswave.springjwt.services;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.models.Comment;
import com.example.creswave.springjwt.repository.BlogRepository;
import com.example.creswave.springjwt.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment addComment(Comment comment,Long blogId){
        Blog blog1 = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("this blog does not exist"));
        comment.setBlog(blog1);
        comment.setId(comment.getId());
        comment.setComment(comment.getComment());
        return commentRepository.save(comment);
    }

}
