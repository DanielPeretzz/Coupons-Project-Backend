package com.example.couponsproject;

import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) throws UserValidationException, EntityExistException, CouponExpirationDateArrived {
        ApplicationContext context = SpringApplication.run(CouponsProjectApplication.class, args);

        CouponRepository couponRepository =  context.getBean(CouponRepository.class);





    }
}
