package com.example.couponsproject.repository;


import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByCompanyId(final Long companyId);
    boolean existsByCategory(final Category category);
    boolean existsByTitle(final String title);
    List<Coupon> findByTitle(final String title);
    List<Coupon> findByEndDate(final LocalDate localDate);
    List<Coupon> findByCategoryAndCompanyId(final Category category, final Long companyId);


}
