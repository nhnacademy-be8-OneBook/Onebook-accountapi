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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberAdaptor memberAdaptor;
    private final JwtUtils jwtUtils;

    // 이거 아닌거 같은데 일단 써보자.
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> auth(@RequestBody LoginRequest loginRequest){
        log.info("Login Request : {}", loginRequest);

        MemberLoginDto memberLoginDto = new MemberLoginDto(loginRequest.getId(), loginRequest.getPassword());

        ResponseEntity<Boolean> isValid = memberAdaptor.login(memberLoginDto);

        log.info(" is valid {}", Objects.requireNonNull(isValid.getBody()).toString());

        if(Boolean.TRUE.equals(isValid.getBody())){
            // 여기에 Member id로 멤버를 하나 받아오는 거 해놓고
            // Jwt 에 추가하면 되겠구만. 추가하는거는 이제 그 프론트나, 다른곳에서 필요한 정보들, ex ) 등급 ..


//            return ResponseEntity.of(Optional.of(jwtUtils.makeJwt(memberLoginDto.loginId())));
            return ResponseEntity.of(Optional.empty());
        }

        throw new RuntimeException("valid 하지 않음 TODO 바꾸기  ");
    }

    @GetMapping("/auth/jwt")
    public ResponseEntity<TokenResponse> getJwtToken(@RequestBody JwtLoginIdRequest request){
        log.info("login id : {}", request.getId());
        // feign check 완료
        ResponseEntity<JwtMemberDto> memberForJWT = memberAdaptor.getMemberForJWT(request.getId());
        // TODO - jwt
        // 안에 들어갈 애들 고치기
        TokenResponse tokenResponse = jwtUtils.makeJwt(memberForJWT.getBody());

        return ResponseEntity.of(Optional.of(tokenResponse));
    }

}
