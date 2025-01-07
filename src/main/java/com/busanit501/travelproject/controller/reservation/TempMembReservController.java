package com.busanit501.travelproject.controller.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class TempMembReservController {
    @GetMapping("/reservation")
    public String reservation() {
        return "/member/reservation";
    }
}
