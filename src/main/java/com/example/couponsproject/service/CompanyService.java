
package com.example.couponsproject.service;

import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.error.excpetion.*;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.couponsproject.util.objectMappingUtil.*;
import static com.example.couponsproject.util.optUtil.optionalCompany;
import static com.example.couponsproject.util.optUtil.optionalCoupon;


@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
//-----------------------------------------------Company-Service--------------------------------------------------------

    //-------------------------------------------Create-Coupon----------------------------------------------------------
    public Coupon createCoupon(CouponDto couponDto) throws EntityExistException, CouponExpirationDateArrived, EntityNotExistException {


        if (!companyRepository.existsById(couponDto.getCompanyId())) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        if (couponDto.getEndDate().isBefore(LocalDate.now())) {
            throw new CouponExpirationDateArrived();
        }

        if (couponRepository.existsByTitle(couponDto.getTitle())) {
            List<Coupon> couponList = couponRepository.findByTitle(couponDto.getTitle());
            for (Coupon coupon : couponList) {
                if (Objects.equals(coupon.getTitle(), couponDto.getTitle()) &&
                        Objects.equals(coupon.getCompany().getId(), couponDto.getCompanyId())) {
                    throw new EntityExistException(EntityType.COUPON);
                }
            }
        }

        log.info("New Coupon created !");
        return couponRepository.save(couponDtoToEntity(couponDto));
    }

    //-------------------------------------------Update-Coupon----------------------------------------------------------

    public void updateCoupon(CouponDto couponDto) throws EntityNotExistException, UserValidationException {
        if (couponDto.getId() == null) {
            throw new UserValidationException();
        }
        if (!couponRepository.existsById(couponDto.getId())) {
            throw new EntityNotExistException(EntityType.COUPON);
        }
        Coupon coupon = optionalCoupon(couponRepository.findById(couponDto.getId()));

        if (couponDto.getCompanyId() == null) {
            assert coupon != null;
            couponDto.setCompanyId(coupon.getCompany().getId());
        }

        if (couponDto.getTitle() == null) {
            assert coupon != null;
            couponDto.setTitle(coupon.getTitle());
        }

        if (couponDto.getStartDate() == null) {
            assert coupon != null;
            couponDto.setStartDate(coupon.getStartDate());
        }

        if (couponDto.getEndDate() == null) {
            assert coupon != null;
            couponDto.setEndDate(coupon.getEndDate());
        }

        if (couponDto.getCategory() == null) {
            assert coupon != null;
            couponDto.setCategory(coupon.getCategory());
        }
        if (couponDto.getDescription() == null){
            assert coupon != null;
            couponDto.setDescription(coupon.getDescription());
        }

        if (couponDto.getAmount() == null) {
            assert coupon != null;
            couponDto.setAmount(coupon.getAmount());
        }

        if (couponDto.getPrice() == null) {
            assert coupon != null;
            couponDto.setPrice(coupon.getPrice());
        }

        assert coupon != null;
        if (couponDto.getImage() == null) {
            couponDto.setImage(coupon.getImage());
        }

        log.info("Coupon Update Successfully");
        couponRepository.save(couponDtoToEntityUpdate(couponDto));

    }

    //-------------------------------------------Delete-Coupon----------------------------------------------------------

    public void deleteCoupon(Long couponId) throws EntityNotExistException {

        if (!couponRepository.existsById(couponId)) {
            throw new EntityNotExistException(EntityType.COUPON);
        }

        log.info(couponId + " has deleted successfully ! ");

        couponRepository.deleteById(couponId);
    }

    //--------------------------------------------Read-all-coupon-by-company-id-----------------------------------------

    public List<CouponDto> readCompanyCoupons(Long companyId) throws EntityNotExistException {

        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        return entityToCouponDto(couponRepository.findByCompanyId(companyId));
    }

    //--------------------------------------------read-Coupon-by-Category-----------------------------------------------

    public List<CouponDto> readCouponByCategory(Long companyId, Category category) throws EntityNotExistException {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }
        if (!couponRepository.existsByCategory(category)) { //fix the method repository
            throw new EntityNotExistException(EntityType.COUPON);
        }
        return entityToCouponDto(couponRepository.findByCompanyId(companyId)
                .stream()
                .filter(coupon -> coupon.getCategory() == category)
                .collect(Collectors.toList()));

    }

    //--------------------------------------------read-Coupon-by-max-price----------------------------------------------

    public List<CouponDto> readCouponUtilPrice(Long companyId, double price) throws EntityNotExistException {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }
        return entityToCouponDto(couponRepository.findByCompanyId(companyId)
                .stream()
                .filter(coupon -> coupon.getPrice() <= price)
                .collect(Collectors.toList()));
    }

    //--------------------------------------------read-company-by-id----------------------------------------------------

    public CompanyDto readCompany(Long companyId) throws EntityNotExistException {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotExistException(EntityType.COMPANY);
        }

        CompanyDto companyDto = entityToCompanyDto(Objects.requireNonNull(optionalCompany(companyRepository.findById(companyId))));

        companyDto.setCouponDtoList(entityToCouponDto(couponRepository.findByCompanyId(companyId)));

        return companyDto;
    }

}

