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
        TestAll();
    }


    public void TestAll() throws ApplicationException {
            boolean adminTests = adminTest.adminTest();
            boolean companyTests = companyTest.companyTest();
            //boolean CustomerTests = customerTest.customerTest();

            System.err.println("Admin test Status : " + adminTests);
            System.err.println("Company test Status :" + companyTests);
            //customerTest.customerTest();

            if (adminTests && companyTests ) {
                System.err.println("Application Test Successfully ! Start Production Mode!");
            }else {
                System.err.println("Application Test Failed ! ");
            }


    }


}









