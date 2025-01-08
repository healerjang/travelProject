package com.busanit501.travelproject.config;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.context.MemberContext;
import com.busanit501.travelproject.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(!parameter.getParameterType().equals(MemberDTO.class)) return false;
        Method method = parameter.getMethod();
        return method != null && method.isAnnotationPresent(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return MemberContext.getMember();
    }
}
