package com.example.couponsproject.dto.listDto;

import com.example.couponsproject.dto.CouponDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponLIstDto {
    private List<CouponDto> couponDtoList;
}
