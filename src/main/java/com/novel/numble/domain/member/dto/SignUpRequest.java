package com.novel.numble.domain.member.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpRequest {
    @Size(min = 2, max = 30, message = "아이디는 2자 - 30자 이어야합니다.")
    String username;
    @Size(min = 6, max = 50, message = "비밀번호는 6자 - 50자 이어야합니다.")
    String password;
}
