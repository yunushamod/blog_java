package com.yunushamod.blog.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid username or password");
    }

    public InvalidCredentialsException(Throwable cause){
        super("Invalid username or password", cause);
    }
}
