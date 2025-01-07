package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.service.member.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewJh1DTO> addReview(@RequestBody ReviewJh1DTO reviewDto) {
        try {
            ReviewJh1DTO savedReview = reviewService.addReview(reviewDto);
            return ResponseEntity.ok(savedReview);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

