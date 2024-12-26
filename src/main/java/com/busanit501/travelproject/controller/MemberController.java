package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.dto.member.LoginDTO;
import com.busanit501.travelproject.dto.member.RegisterDTO;
import com.busanit501.travelproject.service.member.MemberFields;
import com.busanit501.travelproject.service.member.MemberService;
import com.busanit501.travelproject.service.member.ResponseLogin;
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
    public void register(String error, Model model) {model.addAttribute("error", error);}

    @PostMapping("/register")
    public String register(RegisterDTO registerDTO, Model model) {
        final String[] error = {""};
        if (memberService.registerMember(registerDTO)) {
            Map<MemberFields, Boolean> duplicateCheckMap = memberService.duplicateCheck(registerDTO);
            if (duplicateCheckMap.isEmpty()) {
                error[0] = ("data is empty");
                model.addAttribute("error", error[0]);
                log.error("MemberController postMapping register error check to input data 'registerDTO'.");
                return "/member/register";
            }
            duplicateCheckMap.entrySet().stream()
                    .filter(entry -> !entry.getValue())
                    .forEach(entry -> error[0] += "  duplicate " + entry.getKey());
            model.addAttribute("error", error[0]);
            return "/member/register";
        }
        return "/member/login";
    }
    @GetMapping("/login")
    public void login(String error, Model model) {model.addAttribute("error", error);}

    // 해당 코드에서 시큐리티, 쿠키 등으로 사용자의 권한을 유지하는 로직 개발 예정.
    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model) {
        if (memberService.login(loginDTO) == ResponseLogin.FALSE) {
            model.addAttribute("error", "login failed");
            return "/member/login";
        }
        return "/mainPage";
    }

    // 이후 쿠키나 시큐리티에서 memberNo를 받은 뒤 사용자의 정보를 보여주는 로직 개발 예정.
    @GetMapping("/myPage")
    public void myPage(Model model, String error) {
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
