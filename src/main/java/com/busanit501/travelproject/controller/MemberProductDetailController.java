package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.service.member.MemberProductDetailService;
import com.busanit501.travelproject.service.member.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor  // Lombok을 이용해 생성자 주입
public class MemberProductDetailController {

    private final MemberProductDetailService productDetailService;
    private final ReviewService reviewService;

    @GetMapping("/product/detail/{productNo}")
    @Member
    public String getProductDetail(@PathVariable Long productNo, Model model, MemberDTO memberDTO, HttpServletRequest request) {
        if (memberDTO != null) {
            model.addAttribute("member", true);
        }

        ProductJh1DTO product = productDetailService.getProductById(productNo);
        if (product == null) {
            model.addAttribute("errorMessage", "해당 상품을 찾을 수 없습니다.");
        }

        // 리뷰 목록 조회
        List<ReviewJh1DTO> reviews = reviewService.getReviewsByProduct(productNo);

        // 모델에 상품과 리뷰 정보를 추가
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);

        return "member/productDetail";  // 상품과 리뷰 정보를 보여주는 페이지
    }
}
