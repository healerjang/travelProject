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
