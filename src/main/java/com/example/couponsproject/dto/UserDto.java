package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Role;
import lombok.*;

import java.io.Serializable;

@Data
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor

public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String password;
    private Role role;



    public UserDto(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }


}
