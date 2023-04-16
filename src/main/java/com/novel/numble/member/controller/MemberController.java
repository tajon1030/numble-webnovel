package com.novel.numble.member.controller;

import com.novel.numble.member.dto.MemberDTO;
import com.novel.numble.member.dto.SignUpRequest;
import com.novel.numble.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("sign-up")
    public ResponseEntity<MemberDTO> signUp(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(MemberDTO.fromEntity(memberService.signUp(request)));
    }

}
