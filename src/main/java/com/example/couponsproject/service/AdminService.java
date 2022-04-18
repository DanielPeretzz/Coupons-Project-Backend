/*
package com.example.couponsproject.service;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.excpetion.EntityExistException;
import com.example.couponsproject.excpetion.EntityNotExistException;
import com.example.couponsproject.excpetion.UpdateEntityException;
import com.example.couponsproject.excpetion.UserValidationException;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import com.example.couponsproject.util.InputUserValidation;
import com.example.couponsproject.util.objectMappingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.couponsproject.util.optUtil.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;


//-------------------------------------------------Admin-Service--------------------------------------------------------

    //---------------------------------------------Create-Company-------------------------------------------------------
    public Company createCompany(final CompanyDto companyDto) throws EntityExistException, UserValidationException {
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
        log.info("New company created successfully !");
        return companyRepository.save(objectMappingUtil.companyDtoToEntity(companyDto));

    }

    //-------------------------------------------Update-Company---------------------------------------------------------

    public void updateCompany(CompanyDto companyDto) throws UpdateEntityException, UserValidationException
            , EntityExistException {

        if (!companyRepository.existsById(companyDto.getId())) {
            throw new EntityExistException(EntityType.COMPANY);
        }

        if (companyDto.getId() == null) {
            throw new UserValidationException();
        }

        Company getCompany = optionalCompany(companyRepository.findById(companyDto.getId()));

        assert getCompany != null;
        if (!Objects.equals(companyDto.getName(), getCompany.getName())) {
            throw new UpdateEntityException(companyDto.getName());
        }
        companyRepository.save(objectMappingUtil.companyDtoToEntityUpdate(companyDto));
    }

    //-------------------------------------------read-Company-----------------------------------------------------------
    public Company readCompany(Long companyId) throws EntityNotExistException {

        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        List<Coupon> couponList = couponRepository.findByCompanyId(companyId);

        Company company = optionalCompany(companyRepository.findById(companyId));

        assert company != null;

        company.setCouponList(couponList);

        return company;
    }

    //-------------------------------------------delete-company---------------------------------------------------------

    public void deleteCompany(Long companyId) throws EntityNotExistException {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }
        companyRepository.deleteById(companyId);
    }

    //--------------------------------------------read-All-Company------------------------------------------------------
    public List<Company> readAllCompany() {
        return companyRepository.findAll();
    }

    //--------------------------------------------Create-Customer-------------------------------------------------------

    public Customer createCustomer(CustomerDto customerDto) throws EntityExistException, UserValidationException {
        if (!InputUserValidation.isEmailValid(customerDto.getEmail()) ||
                !InputUserValidation.isPasswordValid(customerDto.getPassword())) {
            throw new UserValidationException();
        }

        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new EntityExistException(EntityType.CUSTOMER);
        }
        return customerRepository.save(objectMappingUtil.customerDtoToEntity(customerDto));
    }


    //--------------------------------------------Update-Customer-------------------------------------------------------

    public void updateCustomer(CustomerDto customerDto) throws EntityExistException, UserValidationException {

        if (!customerRepository.existsById(customerDto.getId())) {
            throw new EntityExistException(EntityType.CUSTOMER);
        }
        if (customerDto.getId() == null) {
            throw new UserValidationException();
        }

        Customer customer = objectMappingUtil.customerDtoToEntityUpdate(customerDto);

        customerRepository.saveAndFlush(customer);
    }

    //--------------------------------------------delete-Customer-------------------------------------------------------

    public void deleteCustomer(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        customerRepository.deleteById(customerId);
    }

    //--------------------------------------------read-All-Customer-----------------------------------------------------

    public List<Customer> readAllCustomer() {
        return customerRepository.findAll();
    }

    //--------------------------------------------read-Customer---------------------------------------------------------

    public Customer readCustomer(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        return optionalCustomer(customerRepository.findById(customerId));
    }

}
*/
