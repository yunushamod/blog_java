package com.yunushamod.blog.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPostRequest {
    private String title;
    private String content;
}
