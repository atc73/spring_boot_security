package com.example.demo.api_controller;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String username) {
        super(username + " already exist in database");
    }
}
