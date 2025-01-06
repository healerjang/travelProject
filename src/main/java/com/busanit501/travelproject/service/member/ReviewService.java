package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.ReviewJh1DTO;

import java.util.List;

public interface ReviewService {
    List<ReviewJh1DTO> getReviewsByProduct(Long productNo);
    ReviewJh1DTO addReview(ReviewJh1DTO reviewDto);
}
