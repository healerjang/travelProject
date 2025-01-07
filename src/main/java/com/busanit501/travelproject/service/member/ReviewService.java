package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.ReviewJh1DTO;

import java.util.List;

public interface ReviewService {
    List<ReviewJh1DTO> getReviewsByProduct(Long productNo);

    // 리뷰를 추가하고, 저장된 리뷰 DTO를 반환
    ReviewJh1DTO addReview(ReviewJh1DTO reviewDto);

    // 리뷰를 저장하는 내부 메소드 (일반적으로 외부에서 호출하지 않음)
    ReviewJh1DTO saveReview(ReviewJh1DTO reviewDto);
}


