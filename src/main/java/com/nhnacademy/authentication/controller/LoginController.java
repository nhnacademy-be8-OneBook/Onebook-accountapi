package com.nhnacademy.authentication.controller;

import com.nhnacademy.authentication.adaptor.MemberAdaptor;
import com.nhnacademy.authentication.dto.MemberLoginDto;
import com.nhnacademy.authentication.utils.JwtUtils;
import com.nhnacademy.authentication.dev_Member.Member;
import com.nhnacademy.authentication.dto.LoginRequest;
import com.nhnacademy.authentication.dto.TokenResponse;

import com.nhnacademy.authentication.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final MemberAdaptor memberAdaptor;
    private final JwtUtils jwtUtils;
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> auth(@RequestBody LoginRequest loginRequest){
        log.info("Login Request : {}", loginRequest);

        MemberLoginDto memberLoginDto = new MemberLoginDto(loginRequest.getId(), loginRequest.getPassword());

        ResponseEntity<Boolean> isValid = memberAdaptor.login(memberLoginDto);

        log.info(" is valid {}", Objects.requireNonNull(isValid.getBody()).toString());

        if(Boolean.TRUE.equals(isValid.getBody())){
            return ResponseEntity.of(Optional.of(jwtUtils.makeJwt(memberLoginDto.loginId())));
        }

        throw new RuntimeException("valid 하지 않음 TODO 바꾸기  ");
    }
}
