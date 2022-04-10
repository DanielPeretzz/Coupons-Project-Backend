package com.example.couponsproject.util;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;

import lombok.*;


@Data
@NoArgsConstructor
public class objectMappingUtil {

//------------------------------------------DTO-TO-Entity---------------------------------------------------------------

    public static Company companyDtoToEntity(final CompanyDto companyDto){
        return Company.builder()
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword().hashCode())
                .build();
    }

    public static Company companyDtoToEntityUpdate(final CompanyDto companyDto){
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword().hashCode())
                .build();
    }

    public static Customer customerDtoToEntity(final CustomerDto customerDto){
        return Customer.builder()
                .email(customerDto.getEmail())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword().hashCode())
                .build();
    }


    public static Coupon couponDtoToEntity(final CouponDto couponDto){
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


//------------------------------------------Entity-TO-Dto---------------------------------------------------------------


    public static CouponDto entityToCouponDto(Coupon coupon){
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


    public static CustomerDto entityTOCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(String.valueOf(customer.getPassword()))
                .build();
    }

    public static CompanyDto entityToCompanyDto(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .password(String.valueOf(company.getPassword()))
                .build();
    }

}
