package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductJh1Repository extends JpaRepository<Product, Long> {
  Optional<Product> findProductByProductNo(Long productNo);
}
