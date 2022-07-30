package com.example.couponsproject.util;

import com.example.couponsproject.dto.JwtDto;
import com.example.couponsproject.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpHeaderUtil {

    // return HttpHeader with the relevant token as header

    public static HttpEntity<Void> createHttpHeader( JwtDto jwt){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer " + jwt.getJwt());

        return new HttpEntity<>(headers);
    }
    // return HttpHeader with the relevant token as header
    public static HttpEntity<Object> createHttpHeaderWithBody(JwtDto jwt, Object object){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer " + jwt.getJwt());

        return new HttpEntity<>(object, headers);
    }

}
