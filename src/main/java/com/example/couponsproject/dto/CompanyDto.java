package com.example.couponsproject.dto;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyDto {
    private Long id;
    private String email;
    private String name;
    private String password;
}
