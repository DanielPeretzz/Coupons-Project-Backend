package com.example.couponsproject.excpetion;

import com.example.couponsproject.enums.EntityType;

public class EntityNotExistException extends ApplicationException {

    public EntityNotExistException(EntityType entityType){
        super("This " + entityType + " is not exists!");
    }

}
