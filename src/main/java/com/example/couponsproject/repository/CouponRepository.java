package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByCompanyId(Long companyId);


}
