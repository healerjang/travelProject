package com.busanit501.travelproject.context;

import com.busanit501.travelproject.dto.member.MemberDTO;

public class MemberContext {
    private static final ThreadLocal<MemberDTO> memberHolder = new ThreadLocal<>();

    public static void setMember(MemberDTO member) {
        memberHolder.set(member);
    }

    public static MemberDTO getMember() {
        return memberHolder.get();
    }

    public static void clear() {
        memberHolder.remove();
    }
}
