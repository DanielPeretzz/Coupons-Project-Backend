package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.dto.listDto.CouponLIstDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.FailedToPurchaseException;
import com.example.couponsproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/coupon/{customerId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CouponLIstDto getCoupon(@PathVariable final Long customerId) throws EntityNotExistException {
        return new CouponLIstDto(customerService.getCoupon(customerId));
    }

    @GetMapping("/category/{customerId}/")
    @ResponseStatus(HttpStatus.FOUND)
    public CouponLIstDto getCouponByCategory(@PathVariable(name = "customerId") final Long customerId,
                                               @RequestParam(name = "category") Category category)
            throws EntityNotExistException {
        return new CouponLIstDto(customerService.getCouponByCategory(customerId, category));
    }


    @GetMapping("/price/{customerId}/")
    @ResponseStatus(HttpStatus.FOUND)
    public CouponLIstDto getCouponByPrice(@PathVariable(name = "customerId") final Long customerId,
                                          @RequestParam(name = "price") final double price)
            throws EntityNotExistException {
        return new CouponLIstDto(customerService.getCouponByPrice(customerId, price));
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CustomerDto getCustomer(@PathVariable Long customerId) throws EntityNotExistException {
        return customerService.getCustomer(customerId);
    }
}
