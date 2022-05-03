package com.example.couponsproject.error.excpetion;

public class UserValidationException extends ApplicationException {

    public UserValidationException() {
        super("Invalid input - Please enter according to the correct format");
    }
}
