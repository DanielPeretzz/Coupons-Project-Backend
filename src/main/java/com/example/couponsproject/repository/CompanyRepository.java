package com.example.couponsproject.repository;

import com.example.couponsproject.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>  {
    boolean  existsById(final Long companyId);
    boolean  existsByEmail(final String email);
    boolean  existsByName(final String name);
}
