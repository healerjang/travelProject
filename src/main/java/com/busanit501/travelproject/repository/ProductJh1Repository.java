package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.repository.productJh1.ProductJh1Search;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductJh1Repository extends JpaRepository<Product, Long>, ProductJh1Search {
  Optional<Product> findProductByProductNo(Long productNo);

  @Query("select p.productNo as productNo, p.name as productName, p.imagePath as imagePath from Product p")
  List<Tuple> getProductsWithImages();
}
