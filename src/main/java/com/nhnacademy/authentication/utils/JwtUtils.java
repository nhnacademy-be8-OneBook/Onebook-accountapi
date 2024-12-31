package com.nhnacademy.authentication.utils;

import com.nhnacademy.authentication.dev_Member.JwtMemberDto;
import com.nhnacademy.authentication.dto.TokenResponse;
import com.nhnacademy.authentication.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private final JwtProperties jwtProperties;

    public TokenResponse makeJwt(JwtMemberDto jwtMemberDto){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, jwtProperties.getExpirationTime());

        // TODO - jwt 합쳐지면 여기 수정
        SecretKey key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        String jwtToken = Jwts.builder().claim("id", "test")
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(key)
                .compact();

        return new TokenResponse(jwtToken, jwtProperties.getTokenPrefix(), jwtProperties.getExpirationTime());
    }
}
