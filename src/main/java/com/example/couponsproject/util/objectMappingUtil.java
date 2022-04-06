package com.example.couponsproject.util;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class objectMappingUtil {


    public static Company companyDtoToEntity(final CompanyDto companyDto){
        return Company.builder()
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
                /*.company(couponDto.getCompanyId())*/
                .title(couponDto.getTitle())
                .description(couponDto.getDescription())
                .startDate(couponDto.getStartDate())
                .endDate(couponDto.getEndDate())
                .amount(couponDto.getAmount())
                .price(couponDto.getPrice())
                .image(couponDto.getImage())
                .build();


    }
}
