package com.example.creswave.springjwt.repository;

import com.example.creswave.springjwt.models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogId (Long blogId);
    @Transactional
    void deleteByBlogId(long blogId);
}
