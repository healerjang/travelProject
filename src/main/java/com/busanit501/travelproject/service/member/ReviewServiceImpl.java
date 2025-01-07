package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Review;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.repository.member.ReviewRepository;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductJh1Repository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductJh1Repository productRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
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

        Member member = memberRepository.findById(reviewDto.getMemberNo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid memberNo: " + reviewDto.getMemberNo()));

        Review review = Review.builder()
                .reviewContent(reviewDto.getReviewContent())
                .rating(reviewDto.getRating())
                .product(product)
                .member(member)
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


    @Override
    public ReviewJh1DTO saveReview(ReviewJh1DTO dto) {
        return null;
    }
}
