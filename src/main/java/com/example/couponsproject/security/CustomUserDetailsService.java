package com.example.couponsproject.security;

import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.repository.CompanyRepository;
import com.example.couponsproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.couponsproject.util.objectMappingUtil.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userToSpringSecurityUser(Objects.requireNonNull(getUser(email)));
    }

    private UserDto getUser(final String email) {
        if (companyRepository.existsByEmail(email)){
             return entityToCompanyDto(companyRepository.findByEmail(email));
        }
        else if (customerRepository.existsByEmail(email)){
            return entityTOCustomerDto(customerRepository.findByEmail(email));
        }
        return null;
    }


}