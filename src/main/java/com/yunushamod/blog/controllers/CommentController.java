package com.yunushamod.blog.controllers;

import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.requests.CommentRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.helpers.Constants;
import com.yunushamod.blog.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.BaseUrl + "comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Result<Boolean>> createComment(@Valid @RequestBody CommentRequest commentRequest) throws InvalidCredentialsException {
        var result = commentService.createComment(commentRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result<Boolean>> deleteComment(@PathVariable Long id) throws RecordNotFoundException {
        var result = commentService.deleteComment(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
