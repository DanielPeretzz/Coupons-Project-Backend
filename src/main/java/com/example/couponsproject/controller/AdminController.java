package com.example.couponsproject.controller;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.dto.listDto.CompanyListDto;
import com.example.couponsproject.dto.listDto.CustomerListDto;
import com.example.couponsproject.error.excpetion.EntityExistException;
import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.UpdateEntityException;
import com.example.couponsproject.error.excpetion.UserValidationException;
import com.example.couponsproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.couponsproject.util.objectMappingUtil.entityTOCustomerDto;
import static com.example.couponsproject.util.objectMappingUtil.entityToCompanyDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    //-------------------------------------------------Company--controller----------------------------------------------
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/company")
    public CompanyDto createCompany(@RequestBody final CompanyDto companyDto) throws UserValidationException, EntityExistException {
        return entityToCompanyDto(adminService.createCompany(companyDto));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/company")
    public void updateCompany(@RequestBody final CompanyDto companyDto)
            throws UserValidationException, UpdateEntityException, EntityExistException {
        adminService.updateCompany(companyDto);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/company/{companyId}")
    public CompanyDto readCompany(@PathVariable final Long companyId) throws EntityNotExistException {
        return adminService.readCompany(companyId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/company/{companyId}")
    public void deleteCompany(@PathVariable final Long companyId) throws EntityNotExistException {
        adminService.deleteCompany(companyId);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/company")
    public CompanyListDto readAllCompany() {
        return new CompanyListDto(adminService.readAllCompany());
    }

    //-------------------------------------------------Customer-controller----------------------------------------------

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody final CustomerDto customerDto)
            throws UserValidationException, EntityExistException {
        return adminService.createCustomer(customerDto);
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody CustomerDto customerDto) throws UserValidationException, EntityExistException {
        adminService.updateCustomer(customerDto);
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable final Long customerId) throws EntityNotExistException {
        adminService.deleteCustomer(customerId);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/customer")
    public CustomerListDto getAllCustomer(){
       return new CustomerListDto(adminService.readAllCustomer());
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/customer/{customerId}")
    public CustomerDto getCustomer(@PathVariable final Long customerId) throws EntityNotExistException {
       return adminService.readCustomer(customerId);
    }


}
