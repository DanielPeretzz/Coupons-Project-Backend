package com.example.couponsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CouponsProjectApplication {

    public static void main(String[] args){
        SpringApplication.run(CouponsProjectApplication.class, args);


    }
}
