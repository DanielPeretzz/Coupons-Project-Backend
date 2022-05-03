package com.example.couponsproject.repository;


import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.CouponDto;
import com.example.couponsproject.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByCompanyId(Long companyId);
    List<Coupon> findByCategory(Category category);
    boolean existsByCategory(Category category);

}
