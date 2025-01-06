package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.service.member.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productDetail")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/product/detail/{productNo}")
    public String getReviewsByProduct(@PathVariable Long productNo, Model model) {
        List<ReviewJh1DTO> reviews = reviewService.getReviewsByProduct(productNo);
        model.addAttribute("reviews", reviews);
        return "/member/productDetail";
    }

    @PostMapping("/product/detail/{productNo}/add")
    @ResponseBody
    public ResponseEntity<ReviewJh1DTO> addReview(@PathVariable Long productNo, @RequestBody ReviewJh1DTO reviewDto) {
        reviewDto.setProductNo(productNo);
        ReviewJh1DTO createdReview = reviewService.addReview(reviewDto);
        return ResponseEntity.ok(createdReview);
    }
}
