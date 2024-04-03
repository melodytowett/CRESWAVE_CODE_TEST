package com.example.creswave.springjwt.services;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.models.Comment;
import com.example.creswave.springjwt.repository.BlogRepository;
import com.example.creswave.springjwt.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Comment> viewComment(Long blogId){
        return commentRepository.findByBlogId(blogId);
    }

    public Comment updateComment(Long commentId, Comment comment){
        Comment comment1 = commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("this Comment does not exist"));
        comment1.setComment(comment.getComment());
        return commentRepository.save(comment1);
    }

    public void deleteComment (Long commentId){
        Comment comment1 = commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("this Comment does not exist"));
        commentRepository.delete(comment1);
    }
    public void deleteByBlog (Long blogId){
        commentRepository.deleteByBlogId(blogId);
    }


}
