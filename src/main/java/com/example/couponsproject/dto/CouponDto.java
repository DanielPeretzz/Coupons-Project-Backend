package com.example.couponsproject.dto;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CouponDto {

    private long id;
    private Long companyId;
    private Category category;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private double price;
    private String image;
}
