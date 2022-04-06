package com.example.couponsproject.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
