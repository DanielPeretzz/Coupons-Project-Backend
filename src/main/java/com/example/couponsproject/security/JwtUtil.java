
package com.example.couponsproject.security;

import com.example.couponsproject.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {
    private static final int ONE_HOUR_IN_MILLIS = 1000 * 60 * 60; // time exp token
    private static final String SECRET_KEY = "couponSystemKey"; // secret key

    public static String extractEmail(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(final String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // generate Token with the user details as claims
    public static String generateToken(final UserDto userDto) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("user",userDto);
        return createToken(claims,userDto.getEmail() );
    }

    //create the token with subject email , user obj as claims , set expiration time , secret key & Algorithm = HS256
    private static String createToken(final Map<String, Object> claims,final String email) {
        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ONE_HOUR_IN_MILLIS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //checking if username and token exp are valid
    public static boolean validateToken(final String token, final UserDetails user) {
        final String email = extractEmail(token);
        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    // checking if token Expired
    private static boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }
}
