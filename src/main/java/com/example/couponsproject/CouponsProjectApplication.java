package com.example.couponsproject;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.repository.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class CouponsProjectApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CouponsProjectApplication.class, args);
		CompanyRepository companyRepository = applicationContext.getBean(CompanyRepository.class);
		companyRepository.save(new Company("daniel@gmail.com","iNNpAx","147147"));
	}

}
