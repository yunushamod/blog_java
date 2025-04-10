package com.yunushamod.blog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasePostResponse {
    private Long id;
    private UserResponse author;
    private String title;
    private String content;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
}
