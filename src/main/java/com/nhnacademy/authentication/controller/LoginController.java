package com.nhnacademy.authentication.controller;

import com.nhnacademy.authentication.Utils.JwtUtils;
import com.nhnacademy.authentication.dev_Member.Member;
import com.nhnacademy.authentication.dto.LoginRequest;
import com.nhnacademy.authentication.dto.TokenResponse;

import com.nhnacademy.authentication.properties.JwtProperties;
import com.nhnacademy.authentication.service.MemberService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final JwtUtils jwtUtils;
    @PostMapping("/auth/login")
    public TokenResponse auth(@RequestBody LoginRequest loginRequest){
        log.info("Login Request : {}", loginRequest);
        // 나는 DB 서버에 접속해서 가져오는걸로
        // member service에서 가져오고
        // member가 없으면
        Member member = memberService.findMember(loginRequest.getId());
        if(Objects.isNull(member)){
            // TODO
            throw new RuntimeException("member is null");
        }

        return jwtUtils.makeJwt(member);
    }
}
