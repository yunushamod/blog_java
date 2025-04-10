package com.yunushamod.blog.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String author;
}
