package com.nhnacademy.authentication.dev_Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtMemberDto {

    Long id;
    String loginId;
}
