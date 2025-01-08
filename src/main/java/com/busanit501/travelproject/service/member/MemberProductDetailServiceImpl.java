package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.Review;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationViewJh1DTO;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.repository.member.ReviewRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MemberProductDetailServiceImpl implements MemberProductDetailService {
    private final ProductJh1Repository productRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public MemberProductDetailServiceImpl(ProductJh1Repository productRepository
    , ReservationRepository reservationRepository
    , ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ProductJh1DTO getProductDetail(Long productNo) {
        Product product = productRepository.findById(productNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid productNo: " + productNo));

        return ProductJh1DTO.builder()
                .productNo(product.getProductNo())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .locationNo(product.getLocation().getLocationNo())
                .startDate(product.getStartDate())
                .endDate(product.getEndDate())
                .capacity(product.getCapacity())
                .imagePath(product.getImagePath())
                .reservations(product.getReservations().stream().map(res -> {
                    Reservation reservation = reservationRepository.findById(res.getReservationNo()).orElseThrow();
                    ReservationViewJh1DTO reservationDTO = ReservationViewJh1DTO.builder()
                            .reservationNo(reservation.getReservationNo())
                            .memberNo(reservation.getMember().getMemberNo())
                            .memberName(reservation.getMember().getMemberName())
                            .productNo(reservation.getProduct().getProductNo())
                            .productName(reservation.getProduct().getName())
                            .ReservationOrder(reservation.getReservationOrder())
                            .regDate(reservation.getRegDate())
                            .modDate(reservation.getModDate())
                            .build();
                    return reservationDTO;
                }).collect(Collectors.toList()))
                .reviews(product.getReviews().stream().map(rev -> {
                    Review review = reviewRepository.findById(rev.getReviewNo()).orElseThrow();
                    ReviewJh1DTO reviewDTO = ReviewJh1DTO.builder()
                            .reviewNo(review.getReviewNo())
                            .reviewContent(review.getReviewContent())
                            .rating(review.getRating())
                            .productNo(review.getProduct().getProductNo())
                            .productName(review.getProduct().getName())
                            .memberNo(review.getMember().getMemberNo())
                            .memberName(review.getMember().getMemberName())
                            .regDate(review.getRegDate())
                            .modDate(review.getModDate())
                            .build();
                    return reviewDTO;
                }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductJh1DTO getProductById(Long productNo) {
        return getProductDetail(productNo);  // 기존의 getProductDetail을 활용하여 기본 정보를 반환
    }
}
