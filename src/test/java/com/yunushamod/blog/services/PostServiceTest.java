package com.yunushamod.blog.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yunushamod.blog.dtos.BasePostResponse;
import com.yunushamod.blog.dtos.PostResponse;
import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.requests.EditPostRequest;
import com.yunushamod.blog.dtos.requests.PostRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.models.Post;
import com.yunushamod.blog.models.User;
import com.yunushamod.blog.repositories.PostRepository;
import com.yunushamod.blog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private User mockUser;
    private PostRequest validPostRequest;
    private EditPostRequest validEditPostRequest;
    private Post mockPost;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setUsername("john_doe");

        validPostRequest = new PostRequest();
        validPostRequest.setAuthor("john_doe");
        validPostRequest.setTitle("Sample Title");
        validPostRequest.setContent("Sample Content");

        validEditPostRequest = new EditPostRequest();
        validEditPostRequest.setTitle("Updated Title");
        validEditPostRequest.setContent("Updated Content");

        mockPost = Post.builder()
                .author(mockUser)
                .title("Sample Title")
                .content("Sample Content")
                .build();
    }

    @Test
    void testCreatePost_Success() throws InvalidCredentialsException {
        // Arrange
        when(userRepository.findByUsername(validPostRequest.getAuthor())).thenReturn(Optional.of(mockUser));
        when(postRepository.save(any(Post.class))).thenReturn(mockPost);

        // Act
        Result<Boolean> result = postService.createPost(validPostRequest);

        // Assert
        assertTrue(result.getData());
        verify(postRepository).save(any(Post.class)); // Verify save was called
    }

    @Test
    void testCreatePost_InvalidCredentials() {
        // Arrange
        when(userRepository.findByUsername(validPostRequest.getAuthor())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> postService.createPost(validPostRequest));
        verify(postRepository, never()).save(any(Post.class)); // Ensure save was not called
    }

    @Test
    void testUpdatePost_Success() throws RecordNotFoundException {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.of(mockPost));
        when(postRepository.save(any(Post.class))).thenReturn(mockPost);

        // Act
        Result<Boolean> result = postService.updatePost(1L, validEditPostRequest);

        // Assert
        assertTrue(result.getData());
        assertEquals("Updated Title", mockPost.getTitle());
        assertEquals("Updated Content", mockPost.getContent());
        verify(postRepository).save(any(Post.class)); // Verify save was called
    }

    @Test
    void testUpdatePost_RecordNotFound() {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> postService.updatePost(1L, validEditPostRequest));
        verify(postRepository, never()).save(any(Post.class)); // Ensure save was not called
    }

    @Test
    void testDeletePost_Success() throws RecordNotFoundException {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.of(mockPost));

        // Act
        Result<Boolean> result = postService.deletePost(1L);

        // Assert
        assertTrue(result.getData());
        verify(postRepository).delete(mockPost); // Verify delete was called
    }

    @Test
    void testDeletePost_RecordNotFound() {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> postService.deletePost(1L));
        verify(postRepository, never()).delete(any(Post.class)); // Ensure delete was not called
    }

    @Test
    void testGetPosts_Success() {
        // Arrange
        List<Post> posts = List.of(mockPost);
        when(postRepository.findAll()).thenReturn(posts);
        when(modelMapper.map(any(Post.class), eq(BasePostResponse.class)))
                .thenReturn(new BasePostResponse());

        // Act
        Result<List<BasePostResponse>> result = postService.getPosts();

        // Assert
        assertNotNull(result.getData());
        assertEquals(1, result.getData().size());
        verify(postRepository).findAll(); // Ensure findAll was called
    }

    @Test
    void testGetPost_Success() throws RecordNotFoundException {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.of(mockPost));
        when(modelMapper.map(any(Post.class), eq(PostResponse.class)))
                .thenReturn(new PostResponse());

        // Act
        Result<PostResponse> result = postService.getPost(1L);

        // Assert
        assertNotNull(result.getData());
        verify(postRepository).findById(1L); // Ensure findById was called
    }

    @Test
    void testGetPost_RecordNotFound() {
        // Arrange
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> postService.getPost(1L));
        verify(postRepository).findById(1L); // Ensure findById was called
    }
}

