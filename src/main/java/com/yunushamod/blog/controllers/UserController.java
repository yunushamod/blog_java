package com.yunushamod.blog.controllers;

import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.UserResponse;
import com.yunushamod.blog.dtos.requests.SignInRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.helpers.Constants;
import com.yunushamod.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.BaseUrl + "users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<Result<UserResponse>> login(@Valid @RequestBody SignInRequest request) throws InvalidCredentialsException {
        var result = userService.login(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
