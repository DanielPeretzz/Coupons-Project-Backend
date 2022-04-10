package com.example.couponsproject.service;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.EntityNotExistException;
import com.example.couponsproject.excpetion.UpdateNameException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import com.example.couponsproject.util.InputUserValidation;
import com.example.couponsproject.util.objectMappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;




@Service
@RequiredArgsConstructor
public class AdminService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
/*

//-------------------------------------------------Admin-Service--------------------------------------------------------

    //---------------------------------------------Create-Company-------------------------------------------------------
    public Company createCompany(final CompanyDto companyDto) {
        Company company = null;
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
            company = objectMappingUtil.companyDtoToEntity(companyDto);

            companyRepository.save(company);
        } catch (UserValidationException | EntityExistException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }

    //-------------------------------------------Update-Company---------------------------------------------------------

    public void updateCompany(CompanyDto companyDto) {
        try {
            if (!companyRepository.existsById(companyDto.getId())) {
                throw new EntityExistException(EntityType.COMPANY);
            }

            if (companyDto.getId() == null) {
                throw new UserValidationException();
            }

            Company companyFromSql = optionalCompany(companyRepository.findById(companyDto.getId()));

            assert companyFromSql != null;
            if (!Objects.equals(companyDto.getName(), companyFromSql.getName())) {
                throw new UpdateNameException(companyDto.getName());
            }

        } catch (EntityExistException | UserValidationException | UpdateNameException e) {
            System.err.println(e.getMessage());
        }

        Company company = objectMappingUtil.companyDtoToEntityUpdate(companyDto);
        companyRepository.save(company);
    }

    //-------------------------------------------read-Company-----------------------------------------------------------
    public Company readCompany(Long companyId) {
        Company company = null;
        try {
            if (!companyRepository.existsById(companyId)) {
                throw new EntityNotExistException(EntityType.COMPANY);
            }
            List<Coupon> couponList = couponRepository.findByCompanyId(companyId);

            company = optionalCompany(companyRepository.findById(companyId));

            assert company != null;

            company.setCouponList(couponList);

        } catch (EntityNotExistException e) {
            System.err.println(e.getMessage());
        }

        return company;
    }

    //-------------------------------------------delete-company---------------------------------------------------------

    public void deleteCompany(Long companyId) {
        try {
            if (!companyRepository.existsById(companyId)) {
                throw new EntityNotExistException(EntityType.COMPANY);
            }

            companyRepository.deleteById(companyId);

        } catch (EntityNotExistException e) {
            System.err.println(e.getMessage());
        }
    }

    //--------------------------------------------read-All-Company------------------------------------------------------
    public List<Company> readAllCompany(){
        return companyRepository.findAll();
    }

    //--------------------------------------------Create-Customer-------------------------------------------------------

*/


}
