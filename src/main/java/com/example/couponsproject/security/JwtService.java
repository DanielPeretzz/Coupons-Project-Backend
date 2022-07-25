package com.example.couponsproject.security;

import com.example.couponsproject.beans.Admin;
import com.example.couponsproject.beans.Company;
import com.example.couponsproject.beans.Customer;
import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.repository.AdminRepository;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    public UserDto loadUserByUsername(final String email) throws UsernameNotFoundException {
        return getUser(email);
    }


    private UserDto getUser(final String email) {
        if (companyRepository.existsByEmail(email)) {
            Company company = companyRepository.findByEmail(email);
            return new UserDto(company.getId(), company.getEmail(), company.getRole());
        } else if (customerRepository.existsByEmail(email)) {
            Customer customer = customerRepository.findByEmail(email);
            return new UserDto(customer.getId(), customer.getEmail(), customer.getRole());
        } else if (adminRepository.existsByEmail(email)) {
            Admin admin = adminRepository.findByEmail(email);
            return new UserDto(admin.getId(),admin.getEmail(),admin.getRole());
        }
        return null;
    }
}
