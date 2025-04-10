package com.yunushamod.blog.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.models.Comment;
import com.yunushamod.blog.models.User;
import com.yunushamod.blog.repositories.CommentRepository;
import com.yunushamod.blog.repositories.UserRepository;
import com.yunushamod.blog.dtos.requests.CommentRequest;
import com.yunushamod.blog.dtos.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    private CommentRequest validCommentRequest;
    private User mockUser;
    private Comment mockComment;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setUsername("john_doe");

        validCommentRequest = new CommentRequest();
        validCommentRequest.setAuthor("john_doe");
        validCommentRequest.setComment("This is a comment.");

        mockComment = Comment.builder()
                .comment("This is a comment.")
                .user(mockUser)
                .build();
    }

    @Test
    void testCreateComment_Success() throws InvalidCredentialsException {
        when(userRepository.findByUsername(validCommentRequest.getAuthor())).thenReturn(Optional.of(mockUser));
        when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);

        Result<Boolean> result = commentService.createComment(validCommentRequest);

        assertTrue(result.getData());
        verify(commentRepository).save(any(Comment.class)); // Verify save was called
    }

    @Test
    void testCreateComment_InvalidCredentials() {
        when(userRepository.findByUsername(validCommentRequest.getAuthor())).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> commentService.createComment(validCommentRequest));
        verify(commentRepository, never()).save(any(Comment.class)); // Ensure save was not called
    }

    @Test
    void testDeleteComment_Success() throws RecordNotFoundException {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));

        Result<Boolean> result = commentService.deleteComment(1L);

        assertTrue(result.getData());
        verify(commentRepository).delete(mockComment); // Verify delete was called
    }

    @Test
    void testDeleteComment_RecordNotFound() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> commentService.deleteComment(1L));
        verify(commentRepository, never()).delete(any(Comment.class)); // Ensure delete was not called
    }
}
