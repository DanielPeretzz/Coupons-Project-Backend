package com.example.couponsproject.excpetion;

import com.example.couponsproject.beans.Company;

public class UpdateNameException extends ApplicationException{

    public UpdateNameException(String dtoName) {
        super("You cannot update company name : " + dtoName);
    }
}
