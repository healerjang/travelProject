package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.member.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainPage")
@Log4j2
public class MainPageController {

    @Member
    @GetMapping
    public void mainPage(HttpServletRequest request, MemberDTO memberDTO) {
        log.info("MainPageController mainPage memberDTO : {}", memberDTO);
    }
}
