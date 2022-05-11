package com.example.couponsproject.clr;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.error.excpetion.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j

public class ApplicationTest implements CommandLineRunner {
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args)  {
        adminTest();
    }


    public void adminTest(){
        CompanyDto companyDto = CompanyDto.builder()
                .name("companyTest")
                .email("companyTest@gmail.com")
                .password("test").build();

        final ResponseEntity<CompanyDto> responseEntity = restTemplate.postForEntity
                ("http://localhost:8080/admin/company",companyDto,CompanyDto.class);

        final CompanyDto companyDtoRes = responseEntity.getBody();

        System.out.println(companyDtoRes);
    }

}









