package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.PasswordUpdateDTO;
import com.busanit501.travelproject.dto.member.UpdateDTO;
import com.busanit501.travelproject.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/update")
@Log4j2
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/id/{id}")
    public boolean duplicateId(@PathVariable String id) {
        return memberService.duplicateID(id);
    }

    @GetMapping(value = "/name/{name}")
    public boolean duplicateName(@PathVariable String name) {
        return memberService.duplicateName(name);
    }

    @GetMapping(value = "/email/{email}")
    public boolean duplicateEmail(@PathVariable String email) {
        return memberService.duplicateEmail(email);
    }

    @GetMapping(value = "/phone/{phone}")
    public boolean duplicatePhone(@PathVariable String phone) {
        return memberService.duplicatePhone(phone);
    }

    @Member
    @PutMapping(value = "/id")
    public boolean updateId(@RequestBody String id, HttpServletRequest request, MemberDTO memberDTO) {
        id = id.substring(1, id.length() - 1);
        if (memberDTO == null || !memberService.duplicateID(id)) return false;
        UpdateDTO updateDTO = modelMapper.map(memberDTO, UpdateDTO.class);
        updateDTO.setMemberPoints(memberDTO.getMemberPoint());
        updateDTO.setMemberID(id);
        memberService.updateMember(updateDTO);
        return true;
    }

    @Member
    @PutMapping(value = "/name")
    public boolean updateName(@RequestBody String name, HttpServletRequest request, MemberDTO memberDTO) {
        name = name.substring(1, name.length() - 1);
        if (memberDTO == null || !memberService.duplicateID(name)) return false;
        UpdateDTO updateDTO = modelMapper.map(memberDTO, UpdateDTO.class);
        updateDTO.setMemberPoints(memberDTO.getMemberPoint());
        updateDTO.setMemberName(name);
        memberService.updateMember(updateDTO);
        return true;
    }

    @Member
    @PutMapping(value = "/email")
    public boolean updateEmail(@RequestBody String email, HttpServletRequest request, MemberDTO memberDTO) {
        email = email.substring(1, email.length() - 1);
        if (memberDTO == null || !memberService.duplicateID(email)) return false;
        UpdateDTO updateDTO = modelMapper.map(memberDTO, UpdateDTO.class);
        updateDTO.setMemberPoints(memberDTO.getMemberPoint());
        updateDTO.setMemberEmail(email);
        memberService.updateMember(updateDTO);
        return true;
    }

    @Member
    @PutMapping(value = "/phone")
    public boolean updatePhone(@RequestBody String phone, HttpServletRequest request, MemberDTO memberDTO) {
        phone = phone.substring(1, phone.length() - 1);
        if (memberDTO == null || !memberService.duplicateID(phone)) return false;
        UpdateDTO updateDTO = modelMapper.map(memberDTO, UpdateDTO.class);
        updateDTO.setMemberPoints(memberDTO.getMemberPoint());
        updateDTO.setMemberPhone(phone);
        memberService.updateMember(updateDTO);
        return true;
    }

    @Member
    @PutMapping(value = "/password")
    public boolean updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO, HttpServletRequest request, MemberDTO memberDTO) {
        if (!memberDTO.getMemberPassword().equals(passwordUpdateDTO.getOldPassword())) return false;
        UpdateDTO updateDTO = modelMapper.map(memberDTO, UpdateDTO.class);
        updateDTO.setMemberPassword(passwordUpdateDTO.getNewPassword());
        updateDTO.setMemberPoints(memberDTO.getMemberPoint());
        memberService.updateMember(updateDTO);
        return true;
    }
}
