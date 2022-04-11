package com.example.couponsproject;


import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.EntityNotExistException;
import com.example.couponsproject.excpetion.UpdateNameException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.service.AdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CouponsProjectApplication.class, args);

    }
}
