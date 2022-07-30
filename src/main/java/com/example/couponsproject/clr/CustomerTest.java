package com.example.couponsproject.clr;

import com.example.couponsproject.controller.AuthController;
import com.example.couponsproject.dto.CustomerDto;
import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.listWrapper.CouponLIstWrapper;
import com.example.couponsproject.error.excpetion.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeader;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerTest {
    private final RestTemplate restTemplate;
    private JwtDto jwtDto;
    private final AuthController authController;


    public boolean customerTest() throws ApplicationException {
        try {
            System.err.println("Customer Login Test Status : " + customerLogin());
            System.err.println("Purchase coupon Test Status : " + purchaseCouponTest());
            System.err.println("Get coupon Test Status : " + getCouponTest());
            System.err.println("Get coupon by Category Test Status : " + getCouponByCategoryTest());
            System.err.println("Get coupon by Price Test Status : " + getCouponByPriceTest());
            System.err.println("Get Customer Test Status : " + getCustomerTest());
        } catch (Exception e) {
            System.err.println("Customer test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        return true;
    }

    public boolean customerLogin() throws ApplicationException {
        try {
            jwtDto = authController.authenticate(CustomerDto.builder()
                    .email("customer@gmail.com")
                    .password("123123").build());

        } catch (Exception e) {
            System.err.println("Customer Login Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        return jwtDto != null;
    }

    public boolean purchaseCouponTest() throws ApplicationException {
        try {
            restTemplate.exchange("http://localhost:8080/customer/purchase/2/2",
                    HttpMethod.POST, createHttpHeader(jwtDto), Void.class);

        } catch (Exception e) {
            System.err.println("Customer Purchase Coupon Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("purchase coupon test by customer Successfully ! ");
        return true;
    }

    public boolean getCouponTest() throws ApplicationException {
        final CouponLIstWrapper couponListRes;
        try {

            final ResponseEntity<CouponLIstWrapper> responseEntity = restTemplate
                    .exchange("http://localhost:8080/customer/coupon/2",
                            HttpMethod.GET, createHttpHeader(jwtDto), CouponLIstWrapper.class);

            couponListRes = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Get Coupon Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("get coupon by customer id test Successfully ! : \n" + couponListRes);
        return true;
    }

    public boolean getCouponByCategoryTest() throws ApplicationException {
        final CouponLIstWrapper couponListRes;
        try {
            final ResponseEntity<CouponLIstWrapper> responseEntity = restTemplate
                    .exchange("http://localhost:8080/customer/category//2?category=VACATION",
                            HttpMethod.GET, createHttpHeader(jwtDto), CouponLIstWrapper.class);

            couponListRes = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Get Coupon by Category Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("get coupon by category Successfully ! : \n " + couponListRes);
        return true;
    }

    public boolean getCouponByPriceTest() throws ApplicationException {
        final CouponLIstWrapper couponListRes;
        try {
            final ResponseEntity<CouponLIstWrapper> responseEntity = restTemplate
                    .exchange("http://localhost:8080/customer/price/2?price=40", HttpMethod.GET,
                            createHttpHeader(jwtDto), CouponLIstWrapper.class);

            couponListRes = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Get Coupon by Price Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("get coupon by price Successfully ! : \n " + couponListRes);
        return true;
    }

    public boolean getCustomerTest() throws ApplicationException {
        final CustomerDto customerDtoRes;
        try {

            final ResponseEntity<CustomerDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/customer/2",
                            HttpMethod.GET, createHttpHeader(jwtDto), CustomerDto.class);

            customerDtoRes = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Get Customer by Price Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("get customer Test successfully ! : \n" + customerDtoRes);
        return true;
    }
}
