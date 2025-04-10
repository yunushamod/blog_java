package com.yunushamod.blog.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException() {
        super("Record not found");
    }

    public RecordNotFoundException(Throwable cause) {
        super("Record not found", cause);
    }
}
