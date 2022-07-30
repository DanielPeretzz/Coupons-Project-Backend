package com.example.couponsproject.dto.listWrapper;

import com.example.couponsproject.dto.CompanyDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyListWrapper {
    private List<CompanyDto> companyDtoList;
}
