package com.nhnacademy.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/auth/test")
    public String returnString(@RequestHeader("Authorization") String authorization){

        System.out.println("Authorization Header: " + authorization);

        return "test";
    }

    @GetMapping("/auth/test/header")
    public String getHeader(@RequestHeader("X-USER-ID") String userId){

        System.out.println("User-ID Header: " + userId);

        return "test";
    }
}
