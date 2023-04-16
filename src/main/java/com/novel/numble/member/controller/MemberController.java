package com.novel.numble.member.controller;

import com.novel.numble.member.dto.LoginRequest;
import com.novel.numble.member.dto.LoginResponse;
import com.novel.numble.member.dto.MemberDTO;
import com.novel.numble.member.dto.SignUpRequest;
import com.novel.numble.member.service.MemberService;
import com.novel.numble.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = memberService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/my")
    public ResponseEntity<MemberDTO> my(@AuthenticationPrincipal CustomUserDetails customUser) {
        return ResponseEntity.ok(new MemberDTO(customUser.getUsername()));
    }
}
