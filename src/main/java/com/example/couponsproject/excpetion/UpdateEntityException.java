package com.example.couponsproject.excpetion;

public class UpdateEntityException extends ApplicationException{

    public UpdateEntityException(String dtoName) {
        super("You cannot update : " + dtoName);
    }
}
