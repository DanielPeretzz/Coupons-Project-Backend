package com.example.couponsproject.controller;

import com.example.couponsproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
}
