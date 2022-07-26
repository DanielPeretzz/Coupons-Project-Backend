package com.example.couponsproject.task;

import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import static com.example.couponsproject.constants.Constants.SLEEP_TIME;


@Component
@RequiredArgsConstructor
@Slf4j
public class CouponExpirationDailyJob {
    private final CouponRepository couponRepository;

    @Scheduled(fixedDelay = SLEEP_TIME)
    public void dailyJob() {
        log.info("daily job scan for exp coupon date");
        for (Coupon coupon : couponRepository.findByEndDate(LocalDate.now())) {
                couponRepository.deleteById(coupon.getId());
                log.info(coupon.getId() + " has been deleted , Reason : coupon exp date !");
        }
    }
}
