package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.member.LoginDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.RegisterDTO;
import com.busanit501.travelproject.dto.member.UpdateDTO;

import java.util.Map;

public interface MemberService {
    Map<MemberFields, Boolean> duplicateCheck(RegisterDTO registerDTO);
    boolean registerMember(RegisterDTO registerDTO);
    MemberDTO getMember(long memberNo);
    void updateMember(UpdateDTO updateDTO);
    boolean deleteMember(long memberNo);
    ResponseLogin login(LoginDTO loginDTO);
    boolean duplicateID(String id);
    boolean duplicateName(String name);
    boolean duplicateEmail(String email);
    boolean duplicatePhone(String phone);
}
