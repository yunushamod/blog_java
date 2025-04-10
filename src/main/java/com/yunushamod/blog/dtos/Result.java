package com.yunushamod.blog.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {
    private String message;
    private boolean status;
    @JsonIgnore
    private int statusCode;
    private T data;

    public static <T> Result<T> OK(T data, String message){
        if(StringUtils.isEmpty(message)){
            message = "Operation completed successfully";
        }
        return new Result<T>(message, true, HttpStatus.OK.value(), data);
    }

    public static <T> Result<T> Created(T data, String message){
        if(StringUtils.isEmpty(message)){
            message = "Record created successfully";
        }
        return new Result<T>(message, true, HttpStatus.CREATED.value(), data);
    }

    public static <T> Result<T> Failed(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "An error occurred possibly due to wrong inputs";
        }
        return new Result<T>(message, false, HttpStatus.BAD_REQUEST.value(), data);
    }

    public static <T> Result<T> NotFound(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "Resource not found";
        }
        return new Result<T>(message, false, HttpStatus.NOT_FOUND.value(), data);
    }

    public static <T> Result<T> Unauthorized(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "Unauthorized";
        }
        return new Result<T>(message, false, HttpStatus.UNAUTHORIZED.value(), data);
    }

    public static <T> Result<T> Forbidden(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "You do not have the permission to view this resource";
        }
        return new Result<T>(message, false, HttpStatus.FORBIDDEN.value(), data);
    }

    public static <T> Result<T> Unprocessable(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "Unprocessable entity";
        }
        return new Result<T>(message, false, HttpStatus.UNPROCESSABLE_ENTITY.value(), data);
    }

    public static <T> Result<T> InternalError(String message, T data){
        if(StringUtils.isEmpty(message)){
            message = "An error occurred. Please try again later";
        }
        return new Result<T>(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
    }
}
