package com.yunushamod.blog.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.UserResponse;
import com.yunushamod.blog.dtos.requests.SignInRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.models.User;
import com.yunushamod.blog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private SignInRequest validSignInRequest;
    private User mockUser;
    private UserResponse mockUserResponse;

    @BeforeEach
    void setUp() {
        validSignInRequest = new SignInRequest();
        validSignInRequest.setUsername("john_doe");
        validSignInRequest.setPassword("password123");

        mockUser = new User();
        mockUser.setUsername("john_doe");
        mockUser.setPassword("password123");

        mockUserResponse = new UserResponse();
        mockUserResponse.setUsername("john_doe");
    }

    @Test
    void testLogin_Success() throws InvalidCredentialsException {
        // Arrange
        when(userRepository.findByUsername(validSignInRequest.getUsername()))
                .thenReturn(Optional.of(mockUser));
        when(modelMapper.map(mockUser, UserResponse.class)).thenReturn(mockUserResponse);

        Result<UserResponse> result = userService.login(validSignInRequest);

        assertNotNull(result);
        assertEquals("john_doe", result.getData().getUsername());
        verify(userRepository).findByUsername(validSignInRequest.getUsername()); // Ensure findByUsername was called
    }

    @Test
    void testLogin_InvalidCredentials_IncorrectPassword() {
        mockUser.setPassword("wrongPassword");
        when(userRepository.findByUsername(validSignInRequest.getUsername()))
                .thenReturn(Optional.of(mockUser));

        assertThrows(InvalidCredentialsException.class, () -> userService.login(validSignInRequest));
        verify(userRepository).findByUsername(validSignInRequest.getUsername()); // Ensure findByUsername was called
    }

    @Test
    void testLogin_InvalidCredentials_UserNotFound() {
        when(userRepository.findByUsername(validSignInRequest.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> userService.login(validSignInRequest));
        verify(userRepository).findByUsername(validSignInRequest.getUsername()); // Ensure findByUsername was called
    }
}
