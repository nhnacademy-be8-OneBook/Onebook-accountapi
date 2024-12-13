package com.nhnacademy.authentication.repository;

import com.nhnacademy.authentication.dev_Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberById(String id);
}
