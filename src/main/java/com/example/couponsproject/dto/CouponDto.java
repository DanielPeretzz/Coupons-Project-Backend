package com.example.couponsproject.dto;

import com.example.couponsproject.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CouponDto {

    private Long id;
    private Long companyId;
    private Category category;
    private String title;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private Integer amount;
    private Double price;
    private String image;
}
