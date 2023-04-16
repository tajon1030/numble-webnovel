package com.novel.numble.domain.member.service;


import com.novel.numble.global.error.CustomException;
import com.novel.numble.global.error.ErrorCode;
import com.novel.numble.global.security.JwtTokenUtils;
import com.novel.numble.domain.member.dto.SignUpRequest;
import com.novel.numble.domain.member.entity.Member;
import com.novel.numble.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public Member signUp(SignUpRequest request) { // 유효성 검사 필요
        // 동일한 username 있는지 확인
        memberRepository.findByUsername(request.getUsername())
                .ifPresent(member -> {
                    throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
                });

        // 회원 저장
        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public String login(String userName, String password) {
        Member member = memberRepository.findByUsername(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND,
                        String.format("%s not found", userName)));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_ERROR);
        }

        return JwtTokenUtils.generateToken(userName, secretKey, expiredTimeMs);
    }
}
