package com.example.creswave.springjwt.services;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.models.Comment;
import com.example.creswave.springjwt.repository.BlogRepository;
import com.example.creswave.springjwt.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<Comment> viewComment(Long commentId){
        return commentRepository.findById(commentId);
    }

    public Map<String, Object> viewComment(Long blogId,int page, int size){
        Map<String, Object> response = new HashMap<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> commentPage = commentRepository.findByBlogId(blogId,pageable);
        List<Comment> comments = new ArrayList<>(commentPage.getContent());

        // Sort comments in descending order by ID
        Collections.sort(comments, Comparator.comparingLong(Comment::getId).reversed());

        response.put("comments", comments);
        response.put("currentPage", commentPage.getNumber());
        response.put("totalItems", commentPage.getTotalElements());
        response.put("totalPages", commentPage.getTotalPages());

        return response;
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
