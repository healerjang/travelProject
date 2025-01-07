package com.busanit501.travelproject.controller.cart;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.member.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartPageController {

    @GetMapping("/cart")
    @Member
    public String cartPage(MemberDTO memberDTO, Model model) {
        if (memberDTO != null) {
            model.addAttribute("member", true);
        }
        return "/cart/cartPage";
    }
}
