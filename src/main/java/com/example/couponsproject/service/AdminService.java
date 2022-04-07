package com.example.couponsproject.service;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import com.example.couponsproject.util.InputUserValidation;
import com.example.couponsproject.util.objectMappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;


    public Company createCompany(final CompanyDto companyDto)  {
        try {
            if (!InputUserValidation.isPasswordValid(companyDto.getPassword())) {
                throw new UserValidationException();
            }
            if (!InputUserValidation.isEmailValid(companyDto.getEmail())) {
                throw new UserValidationException();
            }
            if (companyRepository.existsByEmail(companyDto.getEmail())) {
                throw new EntityExistException(EntityType.COMPANY);
            }

            if (companyRepository.existsByName(companyDto.getName())) {
                throw new EntityExistException(EntityType.COMPANY);
            }
        }
        catch (UserValidationException | EntityExistException e) {
            System.err.println(e.getMessage());
        }
        Company company = objectMappingUtil.companyDtoToEntity(companyDto);

        return companyRepository.save(company);

    }


}
