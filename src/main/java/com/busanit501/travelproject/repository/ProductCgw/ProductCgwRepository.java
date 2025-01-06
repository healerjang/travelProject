package com.busanit501.travelproject.repository.ProductCgw;

import com.busanit501.travelproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCgwRepository extends JpaRepository<Product, Long>, ProductCgwSearch {

}
