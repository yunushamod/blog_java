package com.yunushamod.blog.services;

import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.UserResponse;
import com.yunushamod.blog.dtos.requests.SignInRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Result<UserResponse> login(SignInRequest request) throws InvalidCredentialsException{
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(InvalidCredentialsException::new);
        if(Objects.equals(user.getPassword(), request.getPassword())){
            throw new InvalidCredentialsException();
        }
        return Result.OK(modelMapper.map(user, UserResponse.class), null);
    }
}
