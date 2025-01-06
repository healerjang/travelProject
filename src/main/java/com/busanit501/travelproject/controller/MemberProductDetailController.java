package com.busanit501.travelproject.controller;


import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.service.member.MemberProductDetailService;
import com.busanit501.travelproject.service.member.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
@Log4j2
public class MemberProductDetailController {

    private final MemberProductDetailService productDetailService;
    private final ReviewService reviewService;

    @Autowired
    public MemberProductDetailController(MemberProductDetailService productDetailService, ReviewService reviewService)
    {
        this.productDetailService = productDetailService;
        this.reviewService = reviewService;
    }

    @GetMapping("/product/detail/{productNo}")
    public String getProductDetail(@PathVariable Long productNo, Model model) {
        ProductJh1DTO product = productDetailService.getProductById(productNo);
        List<ReviewJh1DTO> reviews = reviewService.getReviewsByProduct(productNo);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);

        return "member/productDetail";
    }
}
