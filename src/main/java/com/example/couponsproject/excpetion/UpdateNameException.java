package com.example.couponsproject.excpetion;

public class UpdateNameException extends ApplicationException{

    public UpdateNameException(String dtoName) {
        super("You cannot update company name : " + dtoName);
    }
}
