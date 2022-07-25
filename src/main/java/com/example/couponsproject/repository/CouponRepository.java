package com.example.couponsproject.repository;


import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByCompanyId(Long companyId);
    boolean existsByCategory(Category category);
    boolean existsByTitle(String title);
    List<Coupon> findByTitle(String title);

}
