package com.example.couponsproject;



import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import com.example.couponsproject.service.AdminService;
import com.example.couponsproject.service.CompanyService;
import com.example.couponsproject.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(CouponsProjectApplication.class, args);
    }
}
