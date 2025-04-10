package com.yunushamod.blog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponse extends BasePostResponse{
    public List<CommentResponse> comments;
}
