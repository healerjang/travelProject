package com.busanit501.travelproject.dto.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class CookieDTO{
    private Cookie cookie;

    public CookieDTO(HttpServletRequest request, String name){
        cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        if(cookie == null) cookie = new Cookie(name, "");
    }

    public static Cookie getUUIDCookie(HttpServletRequest request) {
        return new CookieDTO(request, "UUID").getCookie();
    }

    public static Cookie memberNoCookie(HttpServletRequest request) {
        return new CookieDTO(request, "memberNo").getCookie();
    }

    public static Cookie setCookie(Cookie cookie, String value) {
        cookie.setValue(value);
        cookie.setPath("/");
        cookie.setMaxAge(24*60*60*7);
        return cookie;
    }
}
