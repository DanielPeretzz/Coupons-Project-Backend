package com.example.couponsproject.controller;

import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import com.example.couponsproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("login")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public JwtDto authenticate(@RequestBody final UserDto user) {
        return authService.authenticate(user);
    }
}