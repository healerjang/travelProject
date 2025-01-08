package com.busanit501.travelproject.interceptor;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.context.MemberContext;
import com.busanit501.travelproject.dto.member.LoginDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.util.CookieDTO;
import com.busanit501.travelproject.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ReadMemberInterceptor implements HandlerInterceptor {
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Member memberAnnotation = handlerMethod.getMethodAnnotation(Member.class);

            if (memberAnnotation == null) {
                memberAnnotation = handlerMethod
                        .getBeanType()
                        .getAnnotation(Member.class);
            }

            if (memberAnnotation != null) {
                String hashUUID = CookieDTO.getUUIDCookie(request).getValue();
                String memberNo = CookieDTO.memberNoCookie(request).getValue();

                MemberDTO memberDTO = null;
                if (!hashUUID.isEmpty() && !memberNo.isEmpty()) {
                    memberDTO = memberService.checkMemberUUID(Long.valueOf(memberNo), hashUUID);
                }

                if (memberDTO != null) memberDTO.setResponseLogin(memberService.getResponseLogin(new LoginDTO(memberDTO.getMemberID(), memberDTO.getMemberPassword())));



                MemberContext.setMember(memberDTO);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        MemberContext.clear();
    }
}
