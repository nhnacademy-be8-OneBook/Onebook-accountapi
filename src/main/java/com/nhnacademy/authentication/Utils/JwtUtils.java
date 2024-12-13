package com.nhnacademy.authentication.Utils;

import com.nhnacademy.authentication.dev_Member.Member;
import com.nhnacademy.authentication.dto.TokenResponse;
import com.nhnacademy.authentication.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private final JwtProperties jwtProperties;

    public TokenResponse makeJwt(Member member){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, jwtProperties.getExpirationTime());

        String jwtToken = Jwts.builder().claim("id", member.getMember_id())
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();


        return new TokenResponse(jwtToken, jwtProperties.getTokenPrefix(), jwtProperties.getExpirationTime());
    }
}
