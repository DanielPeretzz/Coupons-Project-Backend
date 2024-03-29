
package com.example.couponsproject.service;

import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.error.excpetion.ApplicationException;
import com.example.couponsproject.security.JwtService;
import com.example.couponsproject.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



    public JwtDto authenticate(final UserDto user) throws ApplicationException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), String.valueOf(user.getPassword().hashCode()))
            );


        } catch (final BadCredentialsException e) {
            throw new ApplicationException("Your email or password are incorrect");
        }

        return new JwtDto(JwtUtil.generateToken(jwtService.loadUserByUsername(user.getEmail())));
    }
}
