package com.yunushamod.blog.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String comment;
    private String author;
}
