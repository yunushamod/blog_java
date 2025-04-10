package com.yunushamod.blog.services;

import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.requests.CommentRequest;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.models.Comment;
import com.yunushamod.blog.repositories.CommentRepository;
import com.yunushamod.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Result<Boolean> createComment(CommentRequest commentRequest) throws InvalidCredentialsException {
        var user = userRepository.findByUsername(commentRequest.getAuthor()).orElseThrow(InvalidCredentialsException::new);
        var comment = Comment.builder().comment(commentRequest.getComment()).user(user).build();
        commentRepository.save(comment);
        return Result.OK(true, null);
    }

    public Result<Boolean> deleteComment(Long id) throws RecordNotFoundException {
        var comment = commentRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        commentRepository.delete(comment);
        return Result.OK(true, null);
    }
}
