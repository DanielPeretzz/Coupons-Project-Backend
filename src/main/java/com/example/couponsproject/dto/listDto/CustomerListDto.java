package com.example.couponsproject.dto.listDto;

import com.example.couponsproject.beans.Customer;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListDto {
    private List<Customer> customerDtoList;
}
