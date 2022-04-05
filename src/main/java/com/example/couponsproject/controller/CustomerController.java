package com.example.couponsproject.controller;

import com.example.couponsproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private CustomerService customerService;
}
