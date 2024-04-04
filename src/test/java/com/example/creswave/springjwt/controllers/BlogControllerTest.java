package com.example.creswave.springjwt.controllers;

import com.example.creswave.springjwt.models.Blog;
import com.example.creswave.springjwt.services.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc; // Injected for simulating web requests

    @MockBean
    private BlogService mockBlogService;

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
    @Test
    public void searchByTitleAndDescription_withValidTerm_returnsBlogs() throws Exception {
        // Arrange
        String searchTerm = "java";
        List<Blog> expectedBlogs = new ArrayList<>(); // Create an empty list
        expectedBlogs.add(new Blog(2L,"Java Tutorial", "Introduction to Java")); // Add sample blogs
        expectedBlogs.add(new Blog(3L,"Spring Boot for Java", "Building Microservices"));

        BlogService mockBlogService = Mockito.mock(BlogService.class);
        Mockito.when(mockBlogService.searchByTitleAndDescription(searchTerm)).thenReturn(expectedBlogs);

        BlogController controller = new BlogController(mockBlogService);
        // Act
        List<Blog> actualBlogs = controller.searchByTitleAndDescription(searchTerm);
        // Assert
        assertEquals(expectedBlogs, actualBlogs);
        Mockito.verify(mockBlogService).searchByTitleAndDescription(searchTerm);
    }

    @Test
    public void updateBlog_withValidData_returnsUpdatedBlog() throws Exception {
        // Arrange
        Long blogId = 1L;
        Blog updatedBlog = new Blog(blogId, "Updated Title", "New Content");
        BlogService mockBlogService = Mockito.mock(BlogService.class);

        Mockito.when(mockBlogService.updateBlog(blogId, updatedBlog)).thenReturn(updatedBlog);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/blogs/{blogId}", blogId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedBlog))) // Assuming Jackson
                .andExpect(status().isOk()) // Expect a 200 OK status code
                .andReturn();

        // Assert
        Blog actualBlog = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Blog.class);
        assertEquals(updatedBlog, actualBlog);
        Mockito.verify(mockBlogService).updateBlog(blogId, updatedBlog);
    }


}