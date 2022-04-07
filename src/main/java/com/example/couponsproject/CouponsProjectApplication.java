package com.example.couponsproject;


import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.service.AdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) throws UserValidationException, EntityExistException {
        SpringApplication.run(CouponsProjectApplication.class, args);

    }

}
