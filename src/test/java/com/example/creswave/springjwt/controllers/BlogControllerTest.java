package com.example.creswave.springjwt.controllers;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.services.BlogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BlogControllerTest {

    @Test
    public void createBlog_withValidBlog_returnsCreatedResponse() {
        // Arrange
        Blog expectedBlog = new Blog(1L,"Title", "Content");
        BlogService mockBlogService = Mockito.mock(BlogService.class);
        Mockito.when(mockBlogService.createBlog(expectedBlog)).thenReturn(expectedBlog);
        BlogController controller = new BlogController(mockBlogService);

        // Act
        ResponseEntity<Blog> response = controller.createBlog(expectedBlog);

        // Assert
        Mockito.verify(mockBlogService).createBlog(expectedBlog);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedBlog, response.getBody());
    }

    @Test
    public void readBlogs_withValidParams_returnsBlogs() throws Exception {
        // Arrange (Set up the test environment)
        int expectedPage = 1;
        int expectedSize = 10;
        Map<String, Object> expectedBlogs = new HashMap<>(); // Mock the expected response

        BlogService mockBlogService = Mockito.mock(BlogService.class);
        Mockito.when(mockBlogService.viewBlogs(expectedPage, expectedSize)).thenReturn(expectedBlogs);

        BlogController controller = new BlogController(mockBlogService);

        // Act (Call the method under test)
        Map<String, Object> response = controller.readBlogs(expectedPage, expectedSize);

        // Assert (Verify the expected outcome)
        assertEquals(expectedBlogs, response); // Verify the returned map is the same as the expected one
        Mockito.verify(mockBlogService).viewBlogs(expectedPage, expectedSize); // Ensure the service method was called with correct arguments
    }


}