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

import static com.example.couponsproject.util.HttpHeaderUtil.createHttpHeaderWithBody;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanyTest {
    private final RestTemplate restTemplate;
    private JwtDto jwtDto;
    private final AuthController authController;


    public boolean companyTest() {
        try {
            System.out.println("Company Login Test : " + companyLoginTest());
            System.out.println("Create Company Status : " + createCouponTest());
            System.out.println("Update Coupon Status : " + updateCouponTest());
      /*  readAllCouponsTest();
        readByCategoryTest();
        readCouponUtilPriceTest();
        readCompanyTest();
        deleteCouponTest();
        createCouponTest();*/
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public boolean companyLoginTest() {

        try {

            jwtDto = authController.authenticate(CompanyDto.builder()
                    .email("companyTest@gmail.com")
                    .password("test").build());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return jwtDto != null;
    }


    public boolean createCouponTest() {
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
            System.err.println("err msg:  " + e.getMessage());
            return false;
        }
        log.info("create coupon test successfully ! : \n " + couponDtoRes);
        return true;
    }

    public boolean updateCouponTest() {
        try {

            final CouponDto couponDto = CouponDto.builder()
                    .id(1L)
                    .title("update")
                    .build();

            restTemplate
                    .exchange("http://localhost:8080/company",HttpMethod.PUT,
                            createHttpHeaderWithBody(jwtDto,couponDto),Void.class);

        } catch (Exception e){
            System.err.println("err msg:  " + e.getMessage());
            return false;
        }
        log.info("coupon update test successfully ! :  \n ");
        return true;
    }

    public void deleteCouponTest() {
        restTemplate.delete("http://localhost:8080/company/delete/1");

        log.info("delete coupon test successfully ! ");
    }

    public void readAllCouponsTest() {
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-coupon/2", CouponLIstDto.class);

        final CouponLIstDto couponList = responseEntity.getBody();

        log.info("read all coupon test Successfully! : \n " + couponList);
    }

    public void readByCategoryTest() {

        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-by-category/2/VACATION", CouponLIstDto.class);

        CouponLIstDto couponDtoList = responseEntity.getBody();
        log.info("read coupon by category test Successfully ! : \n " + couponDtoList);
    }

    public void readCouponUtilPriceTest() {
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-by-price/2/10", CouponLIstDto.class);

        final CouponLIstDto couponList = responseEntity.getBody();

        log.info("read coupon by price Test Successfully! : \n " + couponList);
    }

    public void readCompanyTest() {
        final ResponseEntity<CompanyDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-company/2", CompanyDto.class);

        final CompanyDto companyDto = responseEntity.getBody();

        log.info("read company test Successfully ! :  \n" + companyDto);
    }
}
