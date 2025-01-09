package com.busanit501.travelproject.controller.cart;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.member.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartPageController {

    @GetMapping("/cart")
    @Member
    public String cartPage(MemberDTO memberDTO, Model model, RedirectAttributes redirectAttributes) {
        if (memberDTO != null) {
            model.addAttribute("member", true);
            return "/cart/cartPage";
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/mainPage";
        }
    }
}
