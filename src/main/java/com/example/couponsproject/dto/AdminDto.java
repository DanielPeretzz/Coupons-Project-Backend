package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Role;
import lombok.*;

@Data
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminDto extends UserDto {

    private Long id;
    private String email;
    private String password;
    private Role role;
}
