package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJh1Repository extends JpaRepository<Product, Long> {

    Product findByProductNo(Long productNo);
}
