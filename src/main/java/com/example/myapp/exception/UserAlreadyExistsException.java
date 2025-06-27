package com.example.myapp.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String massage){
        super(massage);
    }
}
