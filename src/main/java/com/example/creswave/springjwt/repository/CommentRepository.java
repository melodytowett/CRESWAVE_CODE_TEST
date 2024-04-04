package com.example.creswave.springjwt.repository;

import com.example.creswave.springjwt.models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByBlogId (Long blogId, Pageable pageable);
    @Transactional
    void deleteByBlogId(long blogId);
}
