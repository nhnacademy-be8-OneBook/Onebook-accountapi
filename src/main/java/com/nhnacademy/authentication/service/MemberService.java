package com.nhnacademy.authentication.service;

import com.nhnacademy.authentication.dev_Member.Member;
import com.nhnacademy.authentication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(String id){
        Optional<Member> memberById = memberRepository.findMemberById(id);


        return memberById.orElse( null);
    }
}
