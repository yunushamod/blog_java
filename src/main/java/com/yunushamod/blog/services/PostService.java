package com.yunushamod.blog.services;

import com.yunushamod.blog.dtos.BasePostResponse;
import com.yunushamod.blog.dtos.PostResponse;
import com.yunushamod.blog.dtos.Result;
import com.yunushamod.blog.dtos.requests.EditPostRequest;
import com.yunushamod.blog.dtos.requests.PostRequest;
import com.yunushamod.blog.dtos.requests.Value;
import com.yunushamod.blog.exceptions.InvalidCredentialsException;
import com.yunushamod.blog.exceptions.RecordNotFoundException;
import com.yunushamod.blog.models.Like;
import com.yunushamod.blog.models.Post;
import com.yunushamod.blog.repositories.LikeRepository;
import com.yunushamod.blog.repositories.PostRepository;
import com.yunushamod.blog.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;

    public Result<Boolean> createPost(PostRequest request) throws InvalidCredentialsException {
        var user = userRepository.findByUsername(request.getAuthor()).orElseThrow(InvalidCredentialsException::new);
        var post = Post.builder().author(user).title(request.getTitle())
                .content(request.getContent()).build();
        postRepository.save(post);
        return Result.Created(true, null);
    }

    public Result<Boolean> updatePost(Long id, EditPostRequest request) throws RecordNotFoundException {
        var post = postRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        if(!StringUtils.isEmpty(request.getTitle())) {
            post.setTitle(request.getTitle());
        }
        if(!StringUtils.isEmpty(request.getContent())) {
            post.setContent(request.getContent());
        }
        postRepository.save(post);
        return Result.OK(true, null);
    }

    public Result<Boolean> likePost(Long id, Value<String> request){
        var post = postRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        var user = userRepository.findByUsername(request.getValue()).orElseThrow(InvalidCredentialsException::new);
        var like = Like.builder().user(user).post(post).build();
        likeRepository.save(like);
        return Result.OK(true, null);
    }

    public Result<Boolean> deletePost(Long id) throws RecordNotFoundException {
        var post = postRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        postRepository.delete(post);
        return Result.OK(true, null);
    }

    public Result<List<BasePostResponse>> getPosts(){
        var posts = postRepository.findAll();
        var result = Stream.of(posts).map(p -> modelMapper.map(p, BasePostResponse.class)).toList();
        return Result.OK(result, null);
    }

    public Result<PostResponse> getPost(Long id) throws RecordNotFoundException{
        var post = postRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        return Result.OK(modelMapper.map(post, PostResponse.class), null);
    }
}
