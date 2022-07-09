package com.example.couponsproject.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyDto extends UserDto{
    private Long id;
    private String email;
    private String name;
    private String password;
    private List<CouponDto> couponDtoList;

}
