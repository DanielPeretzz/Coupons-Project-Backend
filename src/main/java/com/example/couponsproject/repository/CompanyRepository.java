package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>  {
    boolean  existsByEmail(final String email);
    boolean  existsByName(final String name);
    Company findByEmail(final String email);

}
