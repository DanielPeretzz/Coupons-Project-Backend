package com.example.couponsproject.util;

import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpHeaderUtil {

    public static HttpEntity<Void> createHttpHeader( JwtDto jwt){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer " + jwt.getJwt());

        return new HttpEntity<>(headers);
    }

    public static HttpEntity<Object> createHttpHeaderWithBody(JwtDto jwt, Object object){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer " + jwt.getJwt());

        return new HttpEntity<>(object, headers);
    }

}
