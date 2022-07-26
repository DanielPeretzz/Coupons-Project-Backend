package com.example.couponsproject.clr;

import com.example.couponsproject.controller.AdminController;
import com.example.couponsproject.controller.AuthController;
import com.example.couponsproject.dto.AdminDto;
import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.listDto.CouponLIstDto;
import com.example.couponsproject.enums.Category;
import com.example.couponsproject.error.excpetion.ApplicationException;
import com.example.couponsproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeader;
import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeaderWithBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanyTest {
    private final RestTemplate restTemplate;
    private JwtDto jwtDto;
    private final AuthController authController;


    public boolean companyTest() throws ApplicationException {
        try {
            System.out.println("Company Login Test : " + companyLoginTest());
            System.out.println("Create Company Status : " + createCouponTest());
            System.out.println("Update Coupon Status : " + updateCouponTest());
            System.out.println("Read All Coupon status : " + readAllCouponsTest());
            System.out.println("Read all coupons by category Status : " + readByCategoryTest());
            System.out.println("Read all coupons by price Status : " + readCouponUtilPriceTest());
            System.out.println("Read Company Test Status : " + readCompanyTest());
            System.out.println("Delete coupon Test Status : " + deleteCouponTest());
            createCouponTest();
        } catch (Exception e) {
            System.err.println("Company test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        return true;
    }


    public boolean companyLoginTest() throws ApplicationException {

        try {

            jwtDto = authController.authenticate(CompanyDto.builder()
                    .email("companyTest@gmail.com")
                    .password("test").build());

        } catch (Exception e) {
            System.err.println("Company Login Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        return jwtDto != null;
    }


    public boolean createCouponTest() throws ApplicationException {
        final CouponDto couponDtoRes;

        try {
            final CouponDto couponDto = CouponDto.builder()
                    .companyId(2L)
                    .category(Category.VACATION)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now())
                    .image("test.com")
                    .price(10.0)
                    .amount(10)
                    .description("test")
                    .title("test")
                    .build();

            final ResponseEntity<CouponDto> couponDtoResponseEntity = restTemplate
                    .exchange("http://localhost:8080/company", HttpMethod.POST,
                            createHttpHeaderWithBody(jwtDto, couponDto), CouponDto.class);

            couponDtoRes = couponDtoResponseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Create company Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("create coupon test successfully ! : \n " + couponDtoRes);
        return true;
    }

    public boolean updateCouponTest() throws ApplicationException {
        try {

            final CouponDto couponDto = CouponDto.builder()
                    .id(1L)
                    .title("update")
                    .build();

            restTemplate
                    .exchange("http://localhost:8080/company", HttpMethod.PUT,
                            createHttpHeaderWithBody(jwtDto, couponDto), Void.class);

        } catch (Exception e) {
            System.err.println("Update Coupon Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("coupon update test successfully ! :  \n ");
        return true;
    }

    public boolean deleteCouponTest() throws ApplicationException {
        try {
            restTemplate.exchange("http://localhost:8080/company/1",HttpMethod.DELETE
                    , createHttpHeader(jwtDto),Void.class);
        } catch (Exception e) {
            System.out.println("Delete Coupon Test : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("delete coupon test successfully ! ");
        return true;
    }

    public boolean readAllCouponsTest() throws ApplicationException {
        final CouponLIstDto couponList;
        try {
            final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/company/read-coupon/2",HttpMethod.GET,
                            createHttpHeader(jwtDto), CouponLIstDto.class);

            couponList = responseEntity.getBody();
        } catch (Exception e) {
            System.err.println("Read all Coupons test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("read all coupon test Successfully! : \n " + couponList);
        return true;
    }

    public boolean readByCategoryTest() throws ApplicationException {
        final CouponLIstDto couponList;
        try {

            final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/company/read-by-category/2?category=VACATION",HttpMethod.GET,
                            createHttpHeader(jwtDto), CouponLIstDto.class);

            couponList = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Read all Coupons by category test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("read coupon by category test Successfully ! : \n " + couponList);
        return true;

    }

    public boolean readCouponUtilPriceTest() throws ApplicationException {
        final CouponLIstDto couponList;
        try {
            final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/company/read-by-price/2?price=10",
                            HttpMethod.GET,createHttpHeader(jwtDto), CouponLIstDto.class);

            couponList = responseEntity.getBody();

        } catch (Exception e) {
            System.err.println("Read all Coupons by price test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("read coupon by price Test Successfully! : \n " + couponList);
        return true;
    }

    public boolean readCompanyTest() throws ApplicationException {
        final CompanyDto companyDto;
        try {
            final ResponseEntity<CompanyDto> responseEntity = restTemplate
                    .exchange("http://localhost:8080/company/2",HttpMethod.GET,
                            createHttpHeader(jwtDto), CompanyDto.class);

            companyDto = responseEntity.getBody();
        }catch (Exception e){
            System.err.println("Read Company test Status  : " + false);
            throw new ApplicationException(e.getMessage());
        }
        log.info("read company test Successfully ! :  \n" + companyDto);
        return true;
    }
}
