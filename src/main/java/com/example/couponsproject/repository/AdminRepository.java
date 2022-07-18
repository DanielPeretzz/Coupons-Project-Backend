package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Admin;
import com.example.couponsproject.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean  existsByEmail(final String email);
    Admin findByEmail(final String email);
}
