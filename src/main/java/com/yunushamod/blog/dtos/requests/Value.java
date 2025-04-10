package com.yunushamod.blog.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value<T> {
    private T value;
}
