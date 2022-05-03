package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.FailedToPurchaseException;
import com.example.couponsproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/purchase/{customerId}/{couponId}")
    public void purchaseCoupon(@PathVariable(name = "customerId") final Long customerId,
                               @PathVariable(name = "couponId") final Long couponId)
            throws FailedToPurchaseException, EntityNotExistException {
        customerService.purchaseCoupon(customerId, couponId);
    }

    @GetMapping("/get-coupon/{customerId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CouponDto> getCoupon(@PathVariable final Long customerId) throws EntityNotExistException {
        return customerService.getCoupon(customerId);
    }

    @GetMapping("/get-coupon-category/{customerId}/{category}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CouponDto> getCouponByCategory(@PathVariable(name = "customerId") final Long customerId,
                                               @PathVariable(name = "category") Category category)
            throws EntityNotExistException {
        return customerService.getCouponByCategory(customerId, category);
    }


    @GetMapping("/get-by-price/{customerId}/{price}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CouponDto> getCouponByPrice(@PathVariable(name = "customerId") final Long customerId,
                                            @PathVariable(name = "price") final double price)
            throws EntityNotExistException {
        return customerService.getCouponByPrice(customerId, price);
    }

    @GetMapping("/get-customer/{customerId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CustomerDto getCustomer(@PathVariable Long customerId) throws EntityNotExistException {
        return customerService.getCustomer(customerId);
    }
}
