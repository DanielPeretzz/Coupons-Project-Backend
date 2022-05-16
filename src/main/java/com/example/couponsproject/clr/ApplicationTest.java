package com.example.couponsproject.clr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j

public class ApplicationTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminTest adminTest;
    private final CompanyTest companyTest;
    private final CustomerTest customerTest;

    @Override
    public void run(String... args)  {
        TestAll();
    }


    public void TestAll(){
        adminTest.adminTest();
        companyTest.companyTest();
        customerTest.customerTest();
    }


}









