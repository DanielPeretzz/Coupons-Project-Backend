package com.example.couponsproject.controller;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.listWrapper.CouponLIstWrapper;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.couponsproject.util.objectMappingUtil.entityToCouponDto;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CouponDto createCoupon(@RequestBody final CouponDto couponDto)
            throws EntityExistException, CouponExpirationDateArrived, EntityNotExistException {
        return entityToCouponDto(companyService.createCoupon(couponDto));
    }

    @PutMapping()
    public void updateCoupon(@RequestBody final CouponDto couponDto)
            throws UserValidationException, UpdateEntityException, EntityNotExistException {
        companyService.updateCoupon(couponDto);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCoupon(@PathVariable final Long companyId) throws EntityNotExistException {
        companyService.deleteCoupon(companyId);
    }


    @GetMapping("/read-coupon/{companyId}")
    @ResponseStatus(HttpStatus.FOUND)
    public CouponLIstWrapper readAllCoupons(@PathVariable(name = "companyId") final Long companyId)
            throws EntityNotExistException {
        return new CouponLIstWrapper(companyService.readCompanyCoupons(companyId));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-category/{companyId}")
    public CouponLIstWrapper readByCategory(@PathVariable(name = "companyId") final Long companyId ,
                                            @RequestParam() final Category category)
            throws EntityNotExistException {
        return new CouponLIstWrapper(companyService.readCouponByCategory(companyId,category));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/read-by-price/{companyId}")
    public CouponLIstWrapper readCouponUtilPrice(@PathVariable(name = "companyId") Long companyId,
                                                 @RequestParam double price)
                                                throws EntityNotExistException {
        return new CouponLIstWrapper(companyService.readCouponUtilPrice(companyId,price));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{companyId}")
    public CompanyDto readCompany(@PathVariable final Long companyId) throws EntityNotExistException {
    return companyService.readCompany(companyId);
    }
}
