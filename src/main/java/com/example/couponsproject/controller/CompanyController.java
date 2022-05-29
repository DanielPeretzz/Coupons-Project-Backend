package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.listDto.CouponLIstDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.couponsproject.util.objectMappingUtil.entityToCouponDto;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CouponDto createCoupon(@RequestBody final CouponDto couponDto)
            throws EntityExistException, CouponExpirationDateArrived {
        return entityToCouponDto(companyService.createCoupon(couponDto));
    }

    @PutMapping("/update")
    public void updateCoupon(@RequestBody final CouponDto couponDto)
            throws UserValidationException, UpdateEntityException, EntityNotExistException {
        companyService.updateCoupon(couponDto);
    }


    @DeleteMapping("/delete/{companyId}")
    public void deleteCoupon(@PathVariable final Long companyId) throws EntityNotExistException {
        companyService.deleteCoupon(companyId);
    }


    @GetMapping("/read-coupon/{companyId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CouponLIstDto readAllCoupons(@PathVariable(name = "companyId") final Long companyId)
            throws EntityNotExistException {
        return new CouponLIstDto(companyService.readCompanyCoupons(companyId));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-category/{companyId}/{category}")
    public CouponLIstDto readByCategory(@PathVariable(name = "companyId") final Long companyId ,
                                          @PathVariable(name = "category")  final Category category)
            throws EntityNotExistException {
        return new CouponLIstDto(companyService.readCouponByCategory(companyId,category));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-price/{companyId}/{price}")
    public CouponLIstDto readCouponUtilPrice(@PathVariable(name = "companyId") Long companyId,
                                              @PathVariable(name = "price") double price)
                                                throws EntityNotExistException {
        return new CouponLIstDto(companyService.readCouponUtilPrice(companyId,price));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-company/{companyId}")
    public CompanyDto readCompany(@PathVariable final Long companyId) throws EntityNotExistException {
    return companyService.readCompany(companyId);
    }
}
