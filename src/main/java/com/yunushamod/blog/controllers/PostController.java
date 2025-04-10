package com.yunushamod.blog.controllers;

import com.yunushamod.blog.dtos.BasePostResponse;
import com.yunushamod.blog.dtos.PostResponse;
import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.requests.EditPostRequest;
import com.yunushamod.blog.dtos.requests.PostRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.helpers.Constants;
import com.yunushamod.blog.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BaseUrl + "posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Result<List<BasePostResponse>>> getPosts() {
        var result = postService.getPosts();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<Result<PostResponse>> getPost(@PathVariable Long id) throws RecordNotFoundException {
        var result = postService.getPost(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Boolean>> createPost(@Valid @RequestBody PostRequest postRequest) throws InvalidCredentialsException {
        var result = postService.createPost(postRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Result<Boolean>> updatePost(@PathVariable Long id, @Valid @RequestBody EditPostRequest postRequest) throws RecordNotFoundException {
        var result = postService.updatePost(id, postRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
