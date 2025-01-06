package com.busanit501.travelproject.controller.cart;

import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
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
    private static final String CART_COOKIE_NAME = "cart";

    @GetMapping("/add/{productNo}")
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
            String[] values = existValue.split("-");
            if (Arrays.asList(values).contains(productNo.toString())) {
                return;
            }
            Cookie cartCookie = optionalCookie.get();
            cartCookie.setValue(existValue + "-" + productNo.toString());
            cartCookie.setPath("/");
            cartCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(cartCookie);
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
    public void deleteCart(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable Long productNo) {
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) return;
        Cookie cartCookie = optionalCookie.get();
        String existValue = cartCookie.getValue();
        String[] values = existValue.split("-");
        existValue = Arrays.stream(values).filter(value -> !value.equals(productNo.toString())).collect(Collectors.joining("-"));
        if (existValue.isEmpty()) {
            Cookie updateCookie = new Cookie(CART_COOKIE_NAME, existValue);
            updateCookie.setPath("/");
            updateCookie.setMaxAge(0);
            httpServletResponse.addCookie(updateCookie);
        } else {
            Cookie updateCookie = new Cookie(CART_COOKIE_NAME, existValue);
            updateCookie.setPath("/");
            updateCookie.setMaxAge(60 * 60 * 2);
            httpServletResponse.addCookie(updateCookie);
        }
    }

    @PostMapping("/makeReservation")
    public Map<String, Integer> makeReservation(@CookieValue(value = "memberNo", required = false) String memberNoCookie,
                                                HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        List<Long> result = new ArrayList<>();
        Optional<Cookie> optionalCookie = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> CART_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        if (optionalCookie.isEmpty()) return Map.of("reservationNoSize", 0);
        ;
        Long memberNo = memberNoCookie != null ? Long.parseLong(memberNoCookie) : null;
        if (memberNo == null) return Map.of("reservationNoSize", 0);
        ;
        Cookie cartCookie = optionalCookie.get();
        List<String> productNos = Arrays.asList(cartCookie.getValue().split("-"));
        productNos.stream().map(Long::parseLong).forEach(productNo -> {
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .memberNo(memberNo)
                    .productNo(productNo)
                    .ReservationOrder(ReservationOrder.PENDING)
                    .build();
            result.add(reservationService.registerReservation(reservationDTO));
        });
        return Map.of("reservationNoSize", result.size());
    }
}
