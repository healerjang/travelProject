package com.busanit501.travelproject.controller.cart;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import com.busanit501.travelproject.service.reservation.ReservationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final AdminJh1Service adminJh1Service;
    private final ReservationService reservationService;
    private final ProductJh1Repository productJh1Repository;
    private static final String CART_COOKIE_NAME = "cart";

    @GetMapping("/add/{productNo}") // 결과는 addResult 에 success, alreadyReserved, alreadyCarted 세개
    public Map<String, String> addToCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable Long productNo) {
        if (productJh1Repository.findProductByProductNo(productNo).isPresent()) return Map.of("addResult", "alreadyReserved");
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) {
            Cookie newCartCookie = new Cookie(CART_COOKIE_NAME, productNo.toString());
            newCartCookie.setPath("/");
            newCartCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(newCartCookie);
            return Map.of("addResult", "success");
        } else {
            String existValue = optionalCookie.get().getValue();
            String[] values = existValue.split("-");
            if (Arrays.asList(values).contains(productNo.toString())) {
                return Map.of("addResult", "alreadyCarted");
            }
            Cookie cartCookie = optionalCookie.get();
            cartCookie.setValue(existValue + "-" + productNo.toString());
            cartCookie.setPath("/");
            cartCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(cartCookie);
            return Map.of("addResult", "success");
        }
    }

    @GetMapping("/read")
    public HcbPageResponseDTO<ProductJh1DTO> readCart(HttpServletRequest httpServletRequest,
                                                      HcbPageRequestDTO hcbPageRequestDTO) {
        List<ProductJh1DTO> dtoList = new ArrayList<>();
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isPresent()) {
            String existValue = optionalCookie.get().getValue();
            String[] productNoArray = existValue.split("-");
            for (String productNo : productNoArray) {
                Long productNoLong = Long.parseLong(productNo);
                ProductJh1DTO result = adminJh1Service.getProductTmp(productNoLong);
                dtoList.add(result);
            }
        }
        HcbPageResponseDTO<ProductJh1DTO> pageResponseDTO = HcbPageResponseDTO.<ProductJh1DTO>builder()
                .dtoList(dtoList)
                .total(dtoList.size())
                .hcbPageRequestDTO(hcbPageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @GetMapping("/del/{productNo}")
    public boolean deleteCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable Long productNo) {
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) return false;
        Cookie cartCookie = optionalCookie.get();
        String existValue = cartCookie.getValue();
        String[] values = existValue.split("-");
        existValue = Arrays.stream(values).filter(value -> !value.equals(productNo.toString())).collect(Collectors.joining("-"));
        if (existValue.isEmpty()) {
            Cookie updateCookie = new Cookie(CART_COOKIE_NAME, existValue);
            updateCookie.setPath("/");
            updateCookie.setMaxAge(0);
            httpServletResponse.addCookie(updateCookie);
            return true;
        } else {
            Cookie updateCookie = new Cookie(CART_COOKIE_NAME, existValue);
            updateCookie.setPath("/");
            updateCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(updateCookie);
            return true;
        }
    }

    @PostMapping("/makeReservation")
    @Member
    public Map<String, Integer> makeReservation(MemberDTO memberDTO, HttpServletRequest httpServletRequest) {
        List<Boolean> result = new ArrayList<>();
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) return Map.of("reservationNoSize", 0);
        if (memberDTO == null) return Map.of("reservationNoSize", 0);
        Cookie cartCookie = optionalCookie.get();
        List<Long> productNoList = Arrays.asList(cartCookie.getValue().split("-")).stream().map(Long::parseLong).toList();
        for (Long productNo : productNoList) {
            if (productJh1Repository.findProductByProductNo(productNo).isPresent()) return Map.of("reservationNoSize", 0);
        }
        for (Long productNo : productNoList) {
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .memberNo(memberDTO.getMemberNo())
                    .productNo(productNo)
                    .ReservationOrder(ReservationOrder.PENDING)
                    .build();
            result.add(reservationService.registerReservation(reservationDTO));
        }
        return Map.of("reservationNoSize", result.size());
    }
}
