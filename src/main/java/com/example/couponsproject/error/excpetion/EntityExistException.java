package com.example.couponsproject.error.excpetion;

import com.example.couponsproject.enums.EntityType;

public class EntityExistException extends  ApplicationException{

    public EntityExistException(EntityType entityType){

        super("This " + entityType + " is already exists!");
    }
}
