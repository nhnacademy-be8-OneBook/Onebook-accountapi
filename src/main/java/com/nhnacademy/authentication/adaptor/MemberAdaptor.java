package com.nhnacademy.authentication.adaptor;


import com.nhnacademy.authentication.dto.MemberLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// TODO task-service-dev -> task-service-prod로 바꿔주기도 해야함
@FeignClient(name = "task-service-dev")
public interface MemberAdaptor {

    //    @PostMapping("/login")
//    public ResponseEntity<MemberLoginDto> postLogin(@RequestBody MemberLoginDto memberLoginDto);
    @PostMapping("/task/auth/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberLoginDto memberLoginDto);

}


