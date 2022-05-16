package com.example.couponsproject.clr;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class AdminTest {
    private final RestTemplate restTemplate;


    public void adminTest() {
        companyCreateTest();
        updateCompanyTest();
        readCompanyTest();
        readAllCompanyTest();
        deleteCompanyTest();
        createCustomerTest();
        updateCustomerTest();
        readCustomerTest();
        readAllCustomerTest();
        deleteCustomerTest();
        companyCreateTest();
        createCustomerTest();
    }

    public void companyCreateTest() {

        CompanyDto companyDto = CompanyDto.builder()
                .name("companyTest")
                .email("companyTest@gmail.com")
                .password("test").build();

        final ResponseEntity<CompanyDto> responseEntity = restTemplate.postForEntity
                ("http://localhost:8080/admin/company", companyDto, CompanyDto.class);

        final CompanyDto companyDtoRes = responseEntity.getBody();

        log.info("create company test successfully! : \n" + companyDtoRes);
    }

    public void updateCompanyTest() {
        CompanyDto companyDto = CompanyDto.builder()
                .id(1L)
                .name("companyTest")
                .email("companyTestUpdate@gmail.com")
                .password("testUpdate").build();

        restTemplate.put("http://localhost:8080/admin/company", companyDto, CompanyDto.class);
        log.info("update company test successfully !");
    }

    public void readCompanyTest() {
        final ResponseEntity<CompanyDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/admin/company/1", CompanyDto.class);

        final CompanyDto companyDtoRes = responseEntity.getBody();
        log.info("read company by id test successfully! :  \n" + companyDtoRes);
    }

    public void deleteCompanyTest() {
        restTemplate.delete("http://localhost:8080/admin/company/1");
        log.info("delete company test successfully !");
    }

    public void readAllCompanyTest() {

       final ResponseEntity<List> responseEntity = restTemplate
               .getForEntity("http://localhost:8080/admin/company", List.class);

       List companyDtoList = responseEntity.getBody();

        log.info("read all test successfully ! : \n " + companyDtoList);
    }

    public void createCustomerTest(){
        final CustomerDto customerDto = CustomerDto.builder()
                .firstName("customer1")
                .lastName("customer2")
                .email("customer@gmail.com")
                .password("123123")
                .couponDtoList(new ArrayList<>()).build();

        final ResponseEntity<CustomerDto> responseEntity = restTemplate
                .postForEntity("http://localhost:8080/admin/customer", customerDto, CustomerDto.class);

        final CustomerDto customerDtoRes = responseEntity.getBody();

        log.info("create customer test successfully ! \n " + customerDtoRes );
    }

    public void updateCustomerTest(){
        final CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstName("update")
                .lastName("update")
                .email("update@gmail.com")
                .password("147147").build();

        restTemplate.put("http://localhost:8080/admin/customer",customerDto,CustomerDto.class);

        log.info("update customer successfully !");
    }

    public void deleteCustomerTest(){
        restTemplate.delete("http://localhost:8080/admin/customer/1");
        log.info("delete customer test successfully !");
    }

    public void readCustomerTest(){
        final ResponseEntity<CustomerDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/admin/customer/1",CustomerDto.class);

        final CustomerDto customerDtoRes = responseEntity.getBody();

        log.info("read customer test successfully ! : \n " + customerDtoRes);
    }

    public void readAllCustomerTest(){
        final ResponseEntity<List> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/admin/customer",List.class);
        List customerList = responseEntity.getBody();
        log.info("read all customer test successfully! : \n"  + customerList);
    }
}
