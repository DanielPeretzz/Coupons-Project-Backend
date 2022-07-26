package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean  existsByEmail(final String Email);
    Customer findByEmail(final String Email);

}
