package com.example.couponsproject.service;

import com.example.couponsproject.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CouponRepository couponRepository;


}
