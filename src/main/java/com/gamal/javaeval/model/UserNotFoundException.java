package com.gamal.javaeval.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class UserNotFoundException extends RuntimeException {
    private HttpStatus status;
    public UserNotFoundException(HttpStatus status,String message){
        super(message);
        this.status=status;
    }
}
