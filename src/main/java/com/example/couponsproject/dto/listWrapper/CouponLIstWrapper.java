package com.example.couponsproject.dto.listWrapper;

import com.example.couponsproject.dto.CouponDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponLIstWrapper {
    private List<CouponDto> couponDtoList;
}
