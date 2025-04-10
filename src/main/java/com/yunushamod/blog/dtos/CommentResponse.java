package com.yunushamod.blog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private UserResponse user;
    private String content;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
