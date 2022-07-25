package com.example.couponsproject;

import com.example.couponsproject.error.excpetion.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) throws UserValidationException, EntityExistException, CouponExpirationDateArrived {
        ApplicationContext context = SpringApplication.run(CouponsProjectApplication.class, args);


    }
}
