package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MemberProductDetailServiceImpl implements MemberProductDetailService {

    private final ProductJh1Repository productRepository;

    @Autowired
    public MemberProductDetailServiceImpl(ProductJh1Repository productRepository) {
        this.productRepository = productRepository;
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
                .reservations(product.getReservations().stream().map(res -> res.getReservationNo()).collect(Collectors.toList()))
                .reviews(product.getReviews().stream().map(rev -> rev.getReviewNo()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductJh1DTO getProductById(Long productNo) {
        return getProductDetail(productNo);  // 기존의 getProductDetail을 활용하여 기본 정보를 반환
    }
}
