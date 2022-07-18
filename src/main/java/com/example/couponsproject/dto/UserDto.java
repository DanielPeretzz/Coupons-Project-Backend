package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Role;
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
