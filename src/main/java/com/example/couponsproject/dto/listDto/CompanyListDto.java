package com.example.couponsproject.dto.listDto;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.dto.CompanyDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyListDto {
    private List<Company> companyDtoList;
}
