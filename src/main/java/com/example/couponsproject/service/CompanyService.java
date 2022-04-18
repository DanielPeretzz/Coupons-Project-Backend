/*
package com.example.couponsproject.service;

import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.excpetion.*;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.couponsproject.util.objectMappingUtil.couponDtoToEntity;
import static com.example.couponsproject.util.objectMappingUtil.couponDtoToEntityUpdate;


@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
//-----------------------------------------------Company-Service--------------------------------------------------------

    //-------------------------------------------Create-Coupon----------------------------------------------------------
    public Coupon createCoupon(CouponDto couponDto) throws EntityExistException, CouponExpirationDateArrived {
        List<Coupon> couponList = couponRepository.findAll();

        if (couponDto.getEndDate().isBefore(LocalDate.now())){
            throw new CouponExpirationDateArrived();
        }

        for (Coupon coupon : couponList) {
            if (Objects.equals(coupon.getTitle(), couponDto.getTitle()) &&
                    Objects.equals(coupon.getCompany().getId(), couponDto.getCompanyId())){
                throw new EntityExistException(EntityType.COUPON);
            }
        }
        log.info("New Coupon created !");
        return couponRepository.save(couponDtoToEntity(couponDto));
    }

    //-------------------------------------------Update-Coupon----------------------------------------------------------

    public void updateCoupon(CouponDto couponDto) throws EntityNotExistException, UserValidationException, UpdateEntityException {
        if (couponDto.getId() == null){
            throw new UserValidationException();
        }
        if (!couponRepository.existsById(couponDto.getId())){
                throw new EntityNotExistException(EntityType.COUPON) ;
        }
        if (!companyRepository.existsById(couponDto.getCompanyId())){
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        Coupon coupon = optionalCoupon(couponRepository.findById(couponDto.getId()));

        assert coupon != null;
        if (!Objects.equals(coupon.getCompany().getId(), couponDto.getCompanyId())){
            throw new UpdateEntityException(String.valueOf(couponDto.getCompanyId()));
        }
        log.info("Coupon Update Successfully");
        couponRepository.save(couponDtoToEntityUpdate(couponDto));

    }

    //-------------------------------------------Delete-Coupon----------------------------------------------------------

    public void deleteCoupon(Long couponId) throws EntityNotExistException {

        if (!couponRepository.existsById(couponId)){
            throw new EntityNotExistException(EntityType.COUPON);
        }

        log.info(couponId + " has deleted successfully ! ");

        couponRepository.deleteById(couponId);
    }
}
*/
