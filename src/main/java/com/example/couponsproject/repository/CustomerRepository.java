package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Coupon;
import com.example.couponsproject.beans.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean  existsByEmail(final String Email);
    Customer findByEmail(final String Email);
}
