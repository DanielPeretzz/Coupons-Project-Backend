package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
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
    public List<CouponDto> readAllCoupons(@PathVariable(name = "companyId") final Long companyId)
            throws EntityNotExistException {
        return companyService.readCompanyCoupons(companyId);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-category/{companyId}/{category}")
    public List<CouponDto> readByCategory(@PathVariable(name = "companyId") final Long companyId ,
                                          @PathVariable(name = "category")  final Category category)
            throws EntityNotExistException {
        return companyService.readCouponByCategory(companyId,category);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-price/{companyId}/{price}")
    public List<CouponDto> readCouponUtilPrice(@PathVariable(name = "companyId") Long companyId,
                                               @PathVariable(name = "price") double price)
                                                throws EntityNotExistException {
        return companyService.readCouponUtilPrice(companyId,price);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-company/{companyId}")
    public CompanyDto readCompany(@PathVariable final Long companyId) throws EntityNotExistException {
    return companyService.readCompany(companyId);
    }
}
