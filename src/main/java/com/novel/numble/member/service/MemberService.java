package com.novel.numble.member.service;


import com.novel.numble.common.exception.CustomException;
import com.novel.numble.common.exception.ErrorCode;
import com.novel.numble.member.dto.SignUpRequest;
import com.novel.numble.member.entity.Member;
import com.novel.numble.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


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
}
