package com.example.creswave.springjwt.controllers;

import com.example.creswave.springjwt.models.Comment;
import com.example.creswave.springjwt.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add/{blogId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment,@PathVariable Long blogId){
        return new ResponseEntity<>(commentService.addComment(comment, blogId), HttpStatus.CREATED);
    }

    @GetMapping("/view/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Optional<Comment> viewComment(@PathVariable Long commentId){
        return commentService.viewComment(commentId);
    }

    @GetMapping("/get-all/{blogId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String,Object> viewAllCommentsForABlog(@PathVariable Long blogId,@RequestParam int page,@RequestParam int size){
        return commentService.viewComment(blogId, page, size);
    }
    @PutMapping("/update/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment,@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.updateComment(commentId, comment),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    @PreAuthorize("hasRole(hasRole('ADMIN')")
    public ResponseEntity<?>deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("comment deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/delete-by-blog/{blogId}")
    @PreAuthorize("hasRole(hasRole('ADMIN')")
    public ResponseEntity<?>deleteCommentByTutorial(@PathVariable Long blogId){
        commentService.deleteByBlog(blogId);
        return new ResponseEntity<>("comments deleted successfully",HttpStatus.OK);
    }

}
