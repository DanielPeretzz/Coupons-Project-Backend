package com.example.couponsproject;

import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.UpdateEntityException;
import com.example.couponsproject.error.excpetion.UserValidationException;
import com.example.couponsproject.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) throws UserValidationException, UpdateEntityException, EntityNotExistException {
       ApplicationContext context =  SpringApplication.run(CouponsProjectApplication.class, args);
        CompanyService companyService = context.getBean(CompanyService.class);


    }
}
