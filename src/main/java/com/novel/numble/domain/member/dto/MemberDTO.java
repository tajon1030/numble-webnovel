package com.novel.numble.domain.member.dto;

import com.novel.numble.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDTO {
    private String username;

    public static MemberDTO fromEntity(Member member) {
        return new MemberDTO(member.getUsername());
    }
}
