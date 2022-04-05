package com.example.couponsproject.controller;

import com.example.couponsproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
}
