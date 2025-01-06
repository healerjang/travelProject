package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.member.LoginDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.RegisterDTO;
import com.busanit501.travelproject.dto.util.CookieDTO;
import com.busanit501.travelproject.service.member.MemberFields;
import com.busanit501.travelproject.service.member.MemberService;
import com.busanit501.travelproject.service.member.ResponseLogin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register(String error, Model model) {
        model.addAttribute("error", error);
        return "/member/login";
    }

    @PostMapping("/register")
    public String register(RegisterDTO registerDTO, Model model) {
       String error;
        if (!memberService.registerMember(registerDTO)) {
            Map<MemberFields, Boolean> duplicateCheckMap = memberService.duplicateCheck(registerDTO);
            if (duplicateCheckMap.isEmpty()) {
                error = "data is empty";
                model.addAttribute("error", error);
                log.error("MemberController postMapping register error check to input data 'registerDTO'.");
                return "/member/login";
            }
            log.info("MemberController register duplicateCheckMap.key : {}", duplicateCheckMap);
            duplicateCheckMap.forEach((key, value) -> model.addAttribute(key.toString(),
                    value ? key : "duplicate" + key));
            return "/member/login";
        }
        model.addAttribute("login", "login");
        return "/member/login";
    }
    @GetMapping("/login")
    public void login(String error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("login", "login");
    }

    // 해당 코드에서 시큐리티, 쿠키 등으로 사용자의 권한을 유지하는 로직 개발 예정.
    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model, HttpServletRequest request, HttpServletResponse response) {
        ResponseLogin responseLogin = memberService.login(loginDTO);

        if (responseLogin == ResponseLogin.FALSE) {
            model.addAttribute("error", "login failed");
            model.addAttribute("login", "login");
            return "/member/login";
        }

        Cookie memberNoCookie = CookieDTO.memberNoCookie(request);
        Cookie UUIDCookie = CookieDTO.getUUIDCookie(request);
        MemberDTO memberDTO = responseLogin.getMemberDTO();

        CookieDTO.setCookie(memberNoCookie, String.valueOf(memberDTO.getMemberNo()));
        CookieDTO.setCookie(UUIDCookie, memberDTO.getMemberUUID());

        response.addCookie(memberNoCookie);
        response.addCookie(UUIDCookie);
        return "redirect:/mainPage";
    }

    // 이후 쿠키나 시큐리티에서 memberNo를 받은 뒤 사용자의 정보를 보여주는 로직 개발 예정.
    @Member
    @GetMapping("/myPage")
    public void myPage(Model model, String error, HttpServletRequest request) {
        model.addAttribute("error", error);
    }

    // 시큐리티 및 쿠키로 사용자 확인 로직 개발 예정
    @PostMapping("/delete")
    public String delete(long memberNo, Model model) {
        if (memberService.deleteMember(memberNo)) return "redirect:/member/login";
        model.addAttribute("error", "delete failed");
        return "/member/myPage";
    }
}
