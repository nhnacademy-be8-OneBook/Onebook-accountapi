package com.nhnacademy.authentication.controller;

import com.nhnacademy.authentication.adaptor.MemberAdaptor;
import com.nhnacademy.authentication.dev_Member.JwtMemberDto;
import com.nhnacademy.authentication.dto.JwtLoginIdRequest;
import com.nhnacademy.authentication.dto.MemberLoginDto;
import com.nhnacademy.authentication.utils.JwtUtils;
import com.nhnacademy.authentication.dto.LoginRequest;
import com.nhnacademy.authentication.dto.TokenResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberAdaptor memberAdaptor;
    private final JwtUtils jwtUtils;

    // 이거 안씀.
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> auth(@RequestBody LoginRequest loginRequest){
        log.info("Login Request : {}", loginRequest);

        MemberLoginDto memberLoginDto = new MemberLoginDto(loginRequest.getId(), loginRequest.getPassword());

        ResponseEntity<Boolean> isValid = memberAdaptor.login(memberLoginDto);

        log.info(" is valid {}", Objects.requireNonNull(isValid.getBody()).toString());

        if(Boolean.TRUE.equals(isValid.getBody())){

//            return ResponseEntity.of(Optional.of(jwtUtils.makeJwt(memberLoginDto.loginId())));
            return ResponseEntity.of(Optional.empty());
        }

        throw new RuntimeException("valid 하지 않음 TODO 바꾸기  ");
    }

    @GetMapping("/auth/jwt")
    public ResponseEntity<TokenResponse> getJwtToken(@RequestParam("id") String id){
        ResponseEntity<JwtMemberDto> memberForJWT = memberAdaptor.getMemberForJWT(id);
        TokenResponse tokenResponse = jwtUtils.makeJwt(memberForJWT.getBody());
        return ResponseEntity.of(Optional.of(tokenResponse));
    }

}
