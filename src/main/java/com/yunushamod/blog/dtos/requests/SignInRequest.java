package com.yunushamod.blog.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
