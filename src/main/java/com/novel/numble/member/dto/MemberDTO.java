package com.novel.numble.member.dto;

import com.novel.numble.member.entity.Member;
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
