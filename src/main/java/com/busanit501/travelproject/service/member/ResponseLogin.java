package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.member.MemberDTO;
import lombok.Getter;

@Getter
public enum ResponseLogin {
    FALSE(null),
    USER(null),
    ADMIN(null);

    private MemberDTO memberDTO;

    ResponseLogin(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }
}
