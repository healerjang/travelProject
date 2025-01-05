package com.busanit501.travelproject.controller.cartPage;

import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final AdminJh1Service adminJh1Service;
    private static final String CART_COOKIE_NAME = "cart";

    @GetMapping("/addToCart/{productNo}")
    public void addToCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable Long productNo) {
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) {
            Cookie newCartCookie = new Cookie(CART_COOKIE_NAME, productNo.toString());
            newCartCookie.setPath("/");
            newCartCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(newCartCookie);
        } else {
            String existValue = optionalCookie.get().getValue();
            String[] values = existValue.split(",");
            if (Arrays.asList(values).contains(productNo.toString())) {
                return;
            }
            Cookie cartCookie = optionalCookie.get();
            cartCookie.setValue(existValue + "," + productNo.toString());
            cartCookie.setPath("/");
            cartCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(cartCookie);
        }
    }

    @GetMapping("/readCart")
    public HcbPageResponseDTO<ProductJh1DTO> readCart(HttpServletRequest httpServletRequest) {
        HcbPageRequestDTO pageRequestDTO = new HcbPageRequestDTO();
        List<ProductJh1DTO> dtoList = new ArrayList<>();
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isPresent()) {
            String existValue = optionalCookie.get().getValue();
            String[] productNoArray = existValue.split(",");
            for (String productNo : productNoArray) {
                Long productNoLong = Long.parseLong(productNo);
                ProductJh1DTO result = adminJh1Service.getProductTmp(productNoLong);
                dtoList.add(result);
            }
        }
        HcbPageResponseDTO<ProductJh1DTO> pageResponseDTO = HcbPageResponseDTO.<ProductJh1DTO>builder()
                .dtoList(dtoList)
                .total(dtoList.size())
                .hcbPageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @GetMapping("/delete/{productNo}")
    public void deleteCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable Long productNo) {
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) return;
        Cookie cartCookie = optionalCookie.get();
        String existValue = cartCookie.getValue();
        String[] values = existValue.split(",");
        existValue = Arrays.stream(values).filter(value -> !value.equals(productNo.toString())).collect(Collectors.joining(","));
        Cookie updateCookie = new Cookie(CART_COOKIE_NAME, existValue);
        updateCookie.setPath("/");
        updateCookie.setMaxAge(60 * 60 * 2);
        httpServletResponse.addCookie(updateCookie);
    }
}
