package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/update")
@Log4j2
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping(value = "/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean duplicateId(@RequestBody String id) {
        return memberService.duplicateID(id);
    }

    @PostMapping(value = "/name", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean duplicateName(@RequestBody String name) {
        return memberService.duplicateName(name);
    }

    @PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean duplicateEmail(@RequestBody String email) {
        return memberService.duplicateEmail(email);
    }

    @PostMapping(value = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean duplicatePhone(@RequestBody String phone) {
        return memberService.duplicatePhone(phone);
    }
    
    //put매핑은 시큐리티, 쿠키 등으로 사용자의 번호를 받은 뒤 수정하는 로직으로 개발예정.

    @PutMapping(value = "/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateId(@RequestBody String id) {
        return memberService.duplicateID(id);
    }

    @PutMapping(value = "/name", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateName(@RequestBody String name) {
        return memberService.duplicateName(name);
    }

    @PutMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateEmail(@RequestBody String email) {
        return memberService.duplicateEmail(email);
    }

    @PutMapping(value = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updatePhone(@RequestBody String phone) {
        return memberService.duplicatePhone(phone);
    }
}
