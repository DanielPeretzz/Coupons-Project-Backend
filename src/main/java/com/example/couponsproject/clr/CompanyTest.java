package com.example.couponsproject.clr;

import com.example.couponsproject.dto.CompanyDto;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.dto.listDto.CouponLIstDto;
import com.example.couponsproject.enums.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanyTest {
    private final RestTemplate restTemplate;


    public void companyTest(){
        createCouponTest();
        updateCouponTest();
        readAllCouponsTest();
        readByCategoryTest();
        readCouponUtilPriceTest();
        readCompanyTest();
        deleteCouponTest();
        createCouponTest();
    }

    public void createCouponTest(){
        final CouponDto couponDto = CouponDto.builder()
                .companyId(2L)
                .category(Category.VACATION)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .image("test.com")
                .price(10)
                .amount(10)
                .description("test")
                .title("test")
                .build();

        final ResponseEntity<CouponDto> couponDtoResponseEntity = restTemplate
                .postForEntity("http://localhost:8080/company/create",couponDto,CouponDto.class);

        final CouponDto couponDtoRes = couponDtoResponseEntity.getBody();

        log.info("create coupon test successfully ! : \n " + couponDtoRes);
    }

    public void updateCouponTest(){
        final CouponDto couponDto = CouponDto.builder()
                .id(1L)
                .title("update")
                .description("update")
                .amount(10)
                .price(10)
                .image("test.com")
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .category(Category.VACATION)
                .companyId(2L)
                .build();

        restTemplate
                .put("http://localhost:8080/company/update",couponDto);

        log.info("coupon update test successfully ! :  \n ");
    }

    public void deleteCouponTest(){
        restTemplate.delete("http://localhost:8080/company/delete/1");

        log.info("delete coupon test successfully ! ");
    }

    public void readAllCouponsTest(){
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-coupon/2",CouponLIstDto.class);

        final CouponLIstDto couponList = responseEntity.getBody();

        log.info("read all coupon test Successfully! : \n " + couponList);
    }
    public void  readByCategoryTest(){

        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-by-category/2/VACATION",CouponLIstDto.class);

        CouponLIstDto couponDtoList = responseEntity.getBody();
        log.info("read coupon by category test Successfully ! : \n " + couponDtoList);
    }

    public void readCouponUtilPriceTest(){
        final ResponseEntity<CouponLIstDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-by-price/2/10",CouponLIstDto.class);

        final CouponLIstDto couponList = responseEntity.getBody();

        log.info("read coupon by price Test Successfully! : \n " + couponList);
    }

    public void readCompanyTest(){
        final ResponseEntity<CompanyDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/company/read-company/2",CompanyDto.class);

        final CompanyDto companyDto = responseEntity.getBody();

        log.info("read company test Successfully ! :  \n" + companyDto);
    }
}
