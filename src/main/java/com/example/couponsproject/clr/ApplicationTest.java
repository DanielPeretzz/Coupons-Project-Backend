package com.example.couponsproject.clr;

import com.example.couponsproject.error.excpetion.ApplicationException;
import com.example.couponsproject.service.AuthService;
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
    private final AuthService authService;

    @Override
    public void run(String... args) throws ApplicationException {
        System.err.println("Application Test Starting.....");
        System.out.println();
        TestAll();
    }


    public void TestAll() throws ApplicationException {
        boolean adminTests = false;
        boolean companyTests = false;
        boolean customerTests = false;

        try {
            adminTests = adminTest.adminTest();
            companyTests = companyTest.companyTest();
            customerTests = customerTest.customerTest();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (adminTests && companyTests && customerTests) {
            System.err.println("Application Summation Test :");
            System.out.println();
            System.err.println("Admin Test Status : " + adminTests);
            System.err.println("Company Test Status : " + companyTests);
            System.err.println("Company Test Status : " + customerTests);
            System.err.println("Application Test Successfully ! Start Production Mode!");
        } else {
            System.err.println("Application Test Failed ! ");
        }


    }


}









