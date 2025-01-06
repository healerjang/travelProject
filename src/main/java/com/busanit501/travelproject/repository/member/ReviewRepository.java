package com.busanit501.travelproject.repository.member;

import com.busanit501.travelproject.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct_ProductNo(Long productNo);
}
