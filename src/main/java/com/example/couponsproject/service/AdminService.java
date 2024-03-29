package com.example.couponsproject.service;

import com.example.couponsproject.beans.Admin;
import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.AdminDto;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.enums.Role;
import com.example.couponsproject.error.excpetion.EntityExistException;
import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.UpdateEntityException;
import com.example.couponsproject.error.excpetion.UserValidationException;
import com.example.couponsproject.repository.AdminRepository;
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

import static com.example.couponsproject.util.objectMappingUtil.*;
import static com.example.couponsproject.util.optUtil.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;


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
        companyDto.setRole(Role.COMPANY);
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

        companyDto.setRole(Role.COMPANY);

        if (companyDto.getEmail() == null){
            assert getCompany != null;
            companyDto.setEmail(getCompany.getEmail());
        }

        if (companyDto.getName() != null) {
            assert getCompany != null;
            if (!Objects.equals(companyDto.getName(), getCompany.getName())) {
                throw new UpdateEntityException(companyDto.getName());
            }
        }
        assert getCompany != null;
        companyDto.setName(getCompany.getName());

        if (companyDto.getPassword() == null) {
            companyDto.setPassword(String.valueOf(getCompany.getPassword()));
            companyRepository.save(companyDtoToEntityUpdateWithoutPass(companyDto));
        } else {
            companyRepository.save(companyDtoToEntityUpdate(companyDto));
        }
    }

    //-------------------------------------------read-Company-----------------------------------------------------------
    public CompanyDto readCompany(Long companyId) throws EntityNotExistException {

        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        CompanyDto companyDto = objectMappingUtil
                .entityToCompanyDto(Objects.requireNonNull(optionalCompany(companyRepository.findById(companyId))));

        List<CouponDto> couponDtoList = entityToCouponDto(couponRepository.findByCompanyId(companyId));

        companyDto.setCouponDtoList(couponDtoList);

        return companyDto;
    }

    //-------------------------------------------delete-company---------------------------------------------------------

    public void deleteCompany(Long companyId) throws EntityNotExistException {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }
        companyRepository.deleteById(companyId);
        log.info(companyId + " has been deleted !");
    }

    //--------------------------------------------read-All-Company------------------------------------------------------
    public List<CompanyDto> readAllCompany() throws EntityNotExistException {

        List<CompanyDto> companyList = objectMappingUtil.entityToListCompanyDto(companyRepository.findAll());

        if (companyList.isEmpty()) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        for (CompanyDto company : companyList) {
            List<CouponDto> couponList = objectMappingUtil
                    .entityToCouponDto(couponRepository.findByCompanyId(company.getId()));
            company.setCouponDtoList(couponList);
        }

        return companyList;
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

        log.info("customer created !");

        customerDto.setRole(Role.CUSTOMER);

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
        Customer customer = optionalCustomer(customerRepository.findById(customerDto.getId()));
        customerDto.setRole(Role.CUSTOMER);
        if (customerDto.getFirstName() == null) {
            assert customer != null;
            customerDto.setFirstName(customer.getFirstName());
        }
        if (customerDto.getLastName() == null) {
            assert customer != null;
            customerDto.setLastName(customer.getLastName());
        }
        if (customerDto.getPassword() == null) {
            assert customer != null;
            customerDto.setPassword(String.valueOf(customer.getPassword()));
            customerRepository.save(objectMappingUtil.customerDtoToEntityUpdateWithoutPass(customerDto));
        } else {
            log.info(customerDto.getEmail() + " updated successfully");
            customerRepository.save(objectMappingUtil.customerDtoToEntityUpdate(customerDto));
        }
    }

    //--------------------------------------------delete-Customer-------------------------------------------------------

    public void deleteCustomer(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        customerRepository.deleteById(customerId);
        log.info(customerId + " has been delete ");
    }

    //--------------------------------------------read-All-Customer-----------------------------------------------------

    public List<CustomerDto> readAllCustomer() throws EntityNotExistException {

        List<CustomerDto> customerDtoList = entityToListCustomerDto(customerRepository.findAll());

        if (customerDtoList.isEmpty()) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        return customerDtoList;
    }

    //--------------------------------------------read-Customer---------------------------------------------------------

    public CustomerDto readCustomer(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        return entityTOCustomerDto(Objects.requireNonNull(optionalCustomer(customerRepository.findById(customerId))));
    }

    //---------------------------------------------create-Admin---------------------------------------------------------

    public Admin createAdmin(final AdminDto adminDto) throws EntityExistException {
        if (adminRepository.existsByEmail(adminDto.getEmail())) {
            throw new EntityExistException(EntityType.ADMIN);
        }
        adminDto.setRole(Role.ADMIN);
        return (adminRepository.save(adminDtoEntity(adminDto)));
    }

}

