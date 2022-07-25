package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Role;
import lombok.*;

import java.util.List;

@Data
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyDto extends UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private List<CouponDto> couponDtoList;
    private Role role;



}
