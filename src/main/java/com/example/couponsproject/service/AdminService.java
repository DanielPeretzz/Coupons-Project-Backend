package com.example.couponsproject.service;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import com.example.couponsproject.util.InputUserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService  {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;


    public Company createCompany(final Company company) {
        Company currentCompany = null;
        try {
            if (!InputUserValidation.isPasswordValid(String.valueOf(company.getPassword()))) {
                throw new UserValidationException();
            }
            if (!InputUserValidation.isEmailValid(company.getEmail())){
                throw new UserValidationException();
            }
            if (companyRepository.findByEmail(company.getEmail()) != null){
                throw new EntityExistException(EntityType.COMPANY);
            }

            if (companyRepository.findByName(company.getName()) != null){
                throw new EntityExistException(EntityType.COMPANY);
            }

            currentCompany = companyRepository.save(company);
        } catch (UserValidationException | EntityExistException e) {
            System.err.println(e.getMessage());
        }
        return currentCompany;
    }



}
