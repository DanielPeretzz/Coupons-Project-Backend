
package com.example.couponsproject.service;

import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public JwtDto authenticate(final UserDto user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), String.valueOf(user.getPassword().hashCode()))
            );
        } catch (final BadCredentialsException e) {
            throw new RuntimeException("Incorrect credentials");
        }

        return new JwtDto(JwtUtil.generateToken(user.getEmail()));
    }
}
