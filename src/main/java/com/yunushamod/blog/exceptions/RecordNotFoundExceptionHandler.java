package com.yunushamod.blog.exceptions;

import com.yunushamod.blog.dtos.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RecordNotFoundExceptionHandler {
    @ExceptionHandler(value = RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Boolean> handleInvalidCredentialsException(RecordNotFoundException ex){
        return Result.Unauthorized(ex.getMessage(), null);
    }
}
