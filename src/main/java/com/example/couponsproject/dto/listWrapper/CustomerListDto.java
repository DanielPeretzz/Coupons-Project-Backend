package com.example.couponsproject.dto.listWrapper;

import com.example.couponsproject.dto.CustomerDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListDto {
    private List<CustomerDto> customerDtoList;
}
