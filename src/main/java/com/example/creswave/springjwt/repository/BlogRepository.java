package com.example.creswave.springjwt.repository;

import com.example.creswave.springjwt.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}
