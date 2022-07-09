package com.example.couponsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
}
