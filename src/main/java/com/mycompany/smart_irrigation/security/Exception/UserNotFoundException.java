package com.mycompany.smart_irrigation.security.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id) {
        super("User does not found with email: " + id);
    }
}
