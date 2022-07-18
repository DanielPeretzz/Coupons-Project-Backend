package com.example.couponsproject;

import com.example.couponsproject.dto.AdminDto;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.service.AdminService;
import com.example.couponsproject.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;


@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args) throws UserValidationException, EntityExistException, CouponExpirationDateArrived {
       ApplicationContext context =  SpringApplication.run(CouponsProjectApplication.class, args);
        AdminService Admin = context.getBean(AdminService.class);

        CompanyService companyService = context.getBean(CompanyService.class);



       Admin.createAdmin(AdminDto.builder()
                .email("Admin@admin.com")
                .password("123456").build());

        Admin.createCompany(CompanyDto.builder().name("alla")
                        .email("company@gmail.com")
                        .password("123456")
                .build());




        companyService.createCoupon(CouponDto.builder().
                companyId(1L).
                category(Category.FOOD).
                title("azzz").
                description("Mzxzxzx").
                startDate(LocalDate.of(2022, 4, 4)).
                endDate(LocalDate.of(2022, 9, 29)).
                amount(22).
                price(450.0).
                image("jdbc:mysql://localhost:3306/coupons_project/6").
                build());



        Admin.createCustomer(CustomerDto
                .builder()
                        .firstName("aaa")
                        .lastName("bbb")
                        .email("customer@gmail.com")
                        .password("123456")
                .build());






    }
}
