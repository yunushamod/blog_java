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
public class InvalidCredentialsExceptionValidationHandler {
    @ExceptionHandler(value = InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<Boolean> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return Result.Unauthorized("Invalid username or password", null);
    }
}

