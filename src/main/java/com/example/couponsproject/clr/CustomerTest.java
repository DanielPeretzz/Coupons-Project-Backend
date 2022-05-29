package com.example.couponsproject.clr;

import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.dto.listDto.CouponLIstDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerTest {
    private final RestTemplate restTemplate;

    public void customerTest(){
        purchaseCouponTest();
        getCouponTest();
        getCouponByCategoryTest();
        getCouponByPriceTest();
        getCustomerTest();
    }

    public void purchaseCouponTest(){
        restTemplate.postForEntity("http://localhost:8080/customer/purchase/2/2",null,null);
        log.info("purchase coupon test by customer Successfully ! ");
    }

    public void getCouponTest(){
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/customer/get-coupon/2",CouponLIstDto.class);

        final CouponLIstDto couponListRes = responseEntity.getBody();

        log.info("get coupon by customer id test Successfully ! : \n" +couponListRes);
    }

    public void getCouponByCategoryTest(){
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/customer/get-coupon-category/2/VACATION",CouponLIstDto.class);

        final CouponLIstDto couponListRes = responseEntity.getBody();

        log.info("get coupon by category Successfully ! : \n " + couponListRes);
    }

    public void getCouponByPriceTest(){
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/customer/get-by-price/2/40",CouponLIstDto.class);

        final CouponLIstDto couponListRes = responseEntity.getBody();

        log.info("get coupon by price Successfully ! : \n " + couponListRes);
    }

    public void getCustomerTest(){
        final ResponseEntity<CustomerDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/customer/get-customer/2",CustomerDto.class);

        final CustomerDto customerDtoRes = responseEntity.getBody();

        log.info("get customer Test successfully ! : \n" + customerDtoRes);
    }
}
