package com.example.couponsproject.service;

import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.enums.EntityType;
import com.example.couponsproject.error.excpetion.EntityNotExistException;
import com.example.couponsproject.error.excpetion.FailedToPurchaseException;
import com.example.couponsproject.repository.CouponRepository;
import com.example.couponsproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.couponsproject.constants.Constants.NEUTRAL_NUMBER;
import static com.example.couponsproject.constants.Constants.REDUCE_AMOUNT;
import static com.example.couponsproject.util.objectMappingUtil.entityTOCustomerDto;
import static com.example.couponsproject.util.objectMappingUtil.entityToCouponDto;
import static com.example.couponsproject.util.optUtil.optionalCoupon;
import static com.example.couponsproject.util.optUtil.optionalCustomer;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
//--------------------------------------------------Customer-Service----------------------------------------------------

    //----------------------------------------------Purchase-Coupon-----------------------------------------------------
    public void purchaseCoupon(final Long customerId, final Long couponId) throws EntityNotExistException,
            FailedToPurchaseException {

        if (!couponRepository.existsById(couponId)) {
            throw new EntityNotExistException(EntityType.COUPON);
        }
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        Customer customer = optionalCustomer(customerRepository.findById(customerId));
        Coupon coupon = optionalCoupon(couponRepository.findById(couponId));

        assert coupon != null;

        if (coupon.getAmount() <= NEUTRAL_NUMBER || coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new FailedToPurchaseException(coupon);
        }

        assert customer != null;
        for (Coupon currentCoupon : customer.getCouponList()) {
            if (Objects.equals(currentCoupon.getId(), coupon.getId())) {
                throw new FailedToPurchaseException(coupon);
            }
        }

        coupon.getCustomerList().add(customer);
        coupon.setAmount(coupon.getAmount() - REDUCE_AMOUNT);
        couponRepository.save(coupon);

        log.info(coupon.getTitle() + " has been purchased !");
    }

    //---------------------------------------------get-Customer-Coupon--------------------------------------------------

    public List<CouponDto> getCoupon(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }
        return entityToCouponDto(Objects.requireNonNull(optionalCustomer(
                customerRepository.findById(customerId))).getCouponList());
    }

    //---------------------------------------------get-coupon-by-category-----------------------------------------------

    public List<CouponDto> getCouponByCategory(Long customerId, Category category) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }

        return entityToCouponDto(Objects.requireNonNull(optionalCustomer(customerRepository.findById(customerId)))
                .getCouponList()
                .stream()
                .filter(coupon -> coupon.getCategory() == category)
                .collect(Collectors.toList()));
    }

    //---------------------------------------------get-coupon-max-price-------------------------------------------------

    public List<CouponDto> getCouponByPrice(Long customerId, double price) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }
        return entityToCouponDto(Objects.requireNonNull(optionalCustomer(customerRepository.findById(customerId))).getCouponList()
                .stream()
                .filter(coupon -> coupon.getPrice() <= price)
                .collect(Collectors.toList()));
    }

    //----------------------------------------------get-Customer--------------------------------------------------------

    public CustomerDto getCustomer(Long customerId) throws EntityNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new EntityNotExistException(EntityType.CUSTOMER);
        }
        return entityTOCustomerDto(Objects.requireNonNull(optionalCustomer(customerRepository.findById(customerId))));
    }
}
