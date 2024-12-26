package com.busanit501.travelproject.advice;

import com.busanit501.travelproject.exception.member.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException ex, RedirectAttributes redirectAttributes) {
        return "redirect:/member/login";
    }
}
