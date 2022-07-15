package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Role;
import lombok.*;

import java.util.List;

@Data
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto extends UserDto  {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<CouponDto> couponDtoList;
    private Role role;

}
