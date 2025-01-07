package com.busanit501.travelproject.repository.member;

import com.busanit501.travelproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

