package com.example.couponsproject;


import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.excpetion.CouponExpirationDateArrived;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.EntityNotExistException;
import com.example.couponsproject.excpetion.UserValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(CouponsProjectApplication.class, args);

    }
}
