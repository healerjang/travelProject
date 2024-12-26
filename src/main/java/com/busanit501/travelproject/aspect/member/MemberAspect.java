package com.busanit501.travelproject.aspect.member;

import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.util.CookieDTO;
import com.busanit501.travelproject.exception.member.UnauthorizedException;
import com.busanit501.travelproject.service.member.MemberService;
import com.busanit501.travelproject.context.MemberContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberAspect {

    private final MemberService memberService;

    @Around("@annotation(com.busanit501.travelproject.annotation.member.Member)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;

        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) request = (HttpServletRequest) arg;
        }

        if (request == null) throw new IllegalArgumentException("Methods annotated with @Member must have HttpServletRequest parameters.");

        String hashUUID = CookieDTO.getUUIDCookie(request).getValue();
        String memberNo = CookieDTO.memberNoCookie(request).getValue();

        if (hashUUID.isEmpty() || memberNo.isEmpty()) throw new UnauthorizedException("No valid hashUUID and memberNo found in cookies.");

        MemberDTO memberDTO = memberService.checkMemberUUID(Long.valueOf(memberNo), hashUUID);

        if (memberDTO == null) {throw new UnauthorizedException("Invalid Member");}

        MemberContext.setMember(memberDTO);
        try { return joinPoint.proceed();}
        finally { MemberContext.clear();}
    }

}
