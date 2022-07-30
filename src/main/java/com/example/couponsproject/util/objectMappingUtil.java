package com.example.couponsproject.util;

import com.example.couponsproject.beans.Admin;
import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.AdminDto;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class objectMappingUtil {

//------------------------------------------DTO-TO-Entity---------------------------------------------------------------
    //This util mapping between Entity to Dto class

    public static Admin adminDtoEntity(AdminDto adminDto) {
        return Admin.builder()
                .email(adminDto.getEmail())
                .password(adminDto.getPassword().hashCode())
                .role(adminDto.getRole()).build();
    }

    public static Company companyDtoToEntity(final CompanyDto companyDto) {
        return Company.builder()
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword().hashCode())
                .role(companyDto.getRole())
                .build();
    }

    public static Company companyDtoToEntityUpdate(final CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword().hashCode())
                .role(companyDto.getRole())
                .build();
    }


    public static Company companyDtoToEntityUpdateWithoutPass(final CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(Integer.valueOf(companyDto.getPassword()))
                .role(companyDto.getRole())
                .build();
    }


    public static Customer customerDtoToEntity(final CustomerDto customerDto) {
        return Customer.builder()
                .email(customerDto.getEmail())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword().hashCode())
                .role(customerDto.getRole())
                .build();
    }

    public static Customer customerDtoToEntityUpdate(final CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword().hashCode())
                .role(customerDto.getRole())
                .build();
    }

    public static Customer customerDtoToEntityUpdateWithoutPass(final CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(Integer.valueOf(customerDto.getPassword()))
                .role(customerDto.getRole())
                .build();
    }


    public static Coupon couponDtoToEntity(final CouponDto couponDto) {
        return Coupon.builder()
                .category(couponDto.getCategory())
                .company(Company.builder().id(couponDto.getCompanyId()).build())
                .title(couponDto.getTitle())
                .description(couponDto.getDescription())
                .startDate(couponDto.getStartDate())
                .endDate(couponDto.getEndDate())
                .amount(couponDto.getAmount())
                .price(couponDto.getPrice())
                .image(couponDto.getImage())
                .build();
    }

    public static Coupon couponDtoToEntityUpdate(final CouponDto couponDto) {
        return Coupon.builder()
                .id(couponDto.getId())
                .category(couponDto.getCategory())
                .company(Company.builder().id(couponDto.getCompanyId()).build())
                .title(couponDto.getTitle())
                .description(couponDto.getDescription())
                .startDate(couponDto.getStartDate())
                .endDate(couponDto.getEndDate())
                .amount(couponDto.getAmount())
                .price(couponDto.getPrice())
                .image(couponDto.getImage())
                .build();
    }


    //------------------------------------------Entity-TO-Dto---------------------------------------------------------------
    //This util mapping between Dto to Entity class

    public static List<CouponDto> entityToCouponDto(List<Coupon> couponList) {
        List<CouponDto> couponDtoList = new ArrayList<>();
        for (Coupon coupon : couponList) {
            couponDtoList.add(entityToCouponDto(coupon));
        }

        return couponDtoList;
    }

    public static CouponDto entityToCouponDto(Coupon coupon) {
        return CouponDto.builder()
                .id(coupon.getId())
                .category(coupon.getCategory())
                .companyId(coupon.getCompany().getId())
                .title(coupon.getTitle())
                .description(coupon.getDescription())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .amount(coupon.getAmount())
                .price(coupon.getPrice())
                .image(coupon.getImage())
                .build();
    }


    public static CustomerDto entityTOCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(String.valueOf(customer.getPassword()))
                .role(customer.getRole())
                .couponDtoList(entityToCouponDto(customer.getCouponList()))
                .build();
    }


    public static CompanyDto entityToCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .password(String.valueOf(company.getPassword()))
                .role(company.getRole())
                .build();
    }


    public static List<CompanyDto> entityToListCompanyDto(List<Company> companyList) {
        List<CompanyDto> companyDtoList = new ArrayList<>();

        for (Company company : companyList) {
            companyDtoList.add(entityToCompanyDto(company));
        }

        return companyDtoList;
    }


    public static List<CustomerDto> entityToListCustomerDto(List<Customer> customerList) {

        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDtoList.add(entityTOCustomerDto(customer));
        }
        return customerDtoList;
    }

}
