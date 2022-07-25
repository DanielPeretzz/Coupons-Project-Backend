
package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.error.excpetion.EntityExistException;
import com.example.couponsproject.error.excpetion.UserValidationException;
import com.example.couponsproject.service.AdminService;
import com.example.couponsproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AdminService adminService;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login")
    public JwtDto authenticate(@RequestBody final UserDto user) {
        return authService.authenticate(user);
    }


 @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/company")
    public JwtDto createCompany(@RequestBody final CompanyDto companyDto) throws UserValidationException, EntityExistException {
         adminService.createCompany(companyDto);
     return authService.authenticate(companyDto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/customer")
    public JwtDto createCustomer(@RequestBody final CustomerDto customerDto) throws UserValidationException, EntityExistException {
        adminService.createCustomer(customerDto);
        return authService.authenticate(customerDto);
    }
}
