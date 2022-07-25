package com.example.couponsproject.clr;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.controller.AuthController;
import com.example.couponsproject.dto.*;
import com.example.couponsproject.dto.listDto.CompanyListDto;
import com.example.couponsproject.dto.listDto.CustomerListDto;
import com.example.couponsproject.enums.Role;
import com.example.couponsproject.service.AdminService;
import com.example.couponsproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeader;
import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeaderWithBody;


@Slf4j
@Component
@RequiredArgsConstructor
public class AdminTest {
    private final RestTemplate restTemplate;
    private JwtDto jwtDto;
    private final AdminService adminService;
    private final AuthController authController;


    public boolean adminTest() {
        try {
            System.err.println("Admin login Test status : " + adminLoginTest());
            System.err.println("Create company Test status : " + companyCreateTest());
            System.err.println("Update company Test status : " + updateCompanyTest());
            System.err.println("Read company Test Status : " + readCompanyTest());
            System.err.println("Read All company Test Status : " + readAllCompanyTest());
            System.err.println("Delete Company Test Status : " + deleteCompanyTest());
            System.err.println("Create Customer Test Status : " + createCustomerTest());
            System.err.println("Update Customer Test Status : " + updateCustomerTest());
            System.err.println("Read Customer Test Status " + readCustomerTest());
            System.err.println("Read All Customer Test Status : " + readAllCustomerTest());
            System.err.println("Delete Customer Test : " + deleteCustomerTest());
            companyCreateTest();
            createCustomerTest();
        } catch (Exception e) {
            return false;
        }
        return true;


    }

    public boolean adminLoginTest() {

        try {
            adminService.createAdmin(AdminDto.builder()
                    .email("Admin@admin.com")
                    .password("123456").build());

            jwtDto = authController.authenticate((AdminDto.builder()
                    .email("Admin@admin.com")
                    .password("123456").build()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return jwtDto != null;
    }

    public boolean companyCreateTest() {

        CompanyDto companyDtoRes;
        try {
            final CompanyDto companyDto = CompanyDto.builder()
                    .name("companyTest")
                    .email("companyTest@gmail.com")
                    .password("test")
                    .build();

            final ResponseEntity<CompanyDto> responseEntity = restTemplate.exchange
                    ("http://localhost:8080/admin/company", HttpMethod.POST,
                            createHttpHeaderWithBody(jwtDto, companyDto), CompanyDto.class);

            companyDtoRes = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println(companyDtoRes);
        return true;
    }

    public boolean updateCompanyTest() {
        try {

            CompanyDto companyDto = CompanyDto.builder()
                    .id(1L)
                    .email("companyTestUpdate@gmail.com")
                    .build();


            restTemplate.exchange("http://localhost:8080/admin/company",
                    HttpMethod.PUT, createHttpHeaderWithBody(jwtDto, companyDto), Company.class);

            log.info("update company test successfully !");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;

    }

    public boolean readCompanyTest() {
        final CompanyDto companyDtoRes;
        try {
            final ResponseEntity<CompanyDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/admin/company/1", HttpMethod.GET,
                            createHttpHeader(jwtDto), CompanyDto.class);
            companyDtoRes = responseEntity.getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("read company by id test successfully! :  \n" + companyDtoRes);
        return true;
    }

    public boolean deleteCompanyTest() {
        try {
            restTemplate.exchange("http://localhost:8080/admin/company/1", HttpMethod.DELETE
                    , createHttpHeader(jwtDto), Void.class);
            log.info("delete company test successfully !");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean readAllCompanyTest() {
        CompanyListDto companyDtoList;
        try {
            final ResponseEntity<CompanyListDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/admin/company",
                            HttpMethod.GET, createHttpHeader(jwtDto), CompanyListDto.class);

            companyDtoList = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("read all test successfully ! : \n " + companyDtoList);
        return true;
    }

    public boolean createCustomerTest() {
        final CustomerDto customerDtoRes;
        try {
            final CustomerDto customerDto = CustomerDto.builder()
                    .firstName("customer1")
                    .lastName("customer2")
                    .email("customer@gmail.com")
                    .password("123123")
                    .build();

            final ResponseEntity<CustomerDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/admin/customer",
                            HttpMethod.POST, createHttpHeaderWithBody(jwtDto, customerDto), CustomerDto.class);

            customerDtoRes = responseEntity.getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("create customer test successfully ! \n " + customerDtoRes);
        return true;

    }

    public boolean updateCustomerTest() {
        try {
            final CustomerDto customerDto = CustomerDto.builder()
                    .id(1L)
                    .lastName("tahat")
                    .email("update@gmail.com")
                    .build();

            restTemplate.exchange("http://localhost:8080/admin/customer", HttpMethod.PUT,
                    createHttpHeaderWithBody(jwtDto, customerDto), CustomerDto.class);


        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("update customer successfully !");
        return true;
    }

    public boolean deleteCustomerTest() {
        try {
            restTemplate.exchange("http://localhost:8080/admin/customer/1",
                    HttpMethod.DELETE, createHttpHeader(jwtDto), Void.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("delete customer test successfully !");
        return true;
    }

    public boolean readCustomerTest() {
        final CustomerDto customerDtoRes;
        try {
            final ResponseEntity<CustomerDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/admin/customer/1", HttpMethod.GET,
                            createHttpHeader(jwtDto), CustomerDto.class);

            customerDtoRes = responseEntity.getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("read customer test successfully ! : \n " + customerDtoRes);
        return true;

    }

    public boolean readAllCustomerTest() {
        CustomerListDto customerList;
        try {
            final ResponseEntity<CustomerListDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/admin/customer",
                            HttpMethod.GET, createHttpHeader(jwtDto), CustomerListDto.class);
            customerList = responseEntity.getBody();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        log.info("read all customer test successfully! : \n" + customerList);
        return true;
    }
}
