package com.example.couponsproject.error;

import com.example.couponsproject.error.excpetion.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public ErrorDetailsDto applicationExceptionHandling(ApplicationException e){
        return new ErrorDetailsDto(e.getMessage());
    }
}
