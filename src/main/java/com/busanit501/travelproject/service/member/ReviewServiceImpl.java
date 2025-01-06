package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Review;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.repository.member.ProductRepository;
import com.busanit501.travelproject.repository.member.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ReviewJh1DTO> getReviewsByProduct(Long productNo) {
        return reviewRepository.findByProduct_ProductNo(productNo)
                .stream()
                .map(review -> ReviewJh1DTO.builder()
                        .reviewNo(review.getReviewNo())
                        .reviewContent(review.getReviewContent())
                        .rating(review.getRating())
                        .productNo(review.getProduct().getProductNo())
                        .memberNo(review.getMember().getMemberNo())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ReviewJh1DTO addReview(ReviewJh1DTO reviewDto) {
        Product product = productRepository.findById(reviewDto.getProductNo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid productNo: " + reviewDto.getProductNo()));

        Review review = Review.builder()
                .reviewContent(reviewDto.getReviewContent())
                .rating(reviewDto.getRating())
                .product(product)
                // Member 정보를 MemberRepository에서 가져와 설정해야 함
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewJh1DTO.builder()
                .reviewNo(savedReview.getReviewNo())
                .reviewContent(savedReview.getReviewContent())
                .rating(savedReview.getRating())
                .productNo(savedReview.getProduct().getProductNo())
                .memberNo(savedReview.getMember().getMemberNo())
                .build();
    }

}
