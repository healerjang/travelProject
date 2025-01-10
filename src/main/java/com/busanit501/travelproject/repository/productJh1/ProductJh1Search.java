package com.busanit501.travelproject.repository.productJh1;

import com.busanit501.travelproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductJh1Search {
  Page<Product> searchProducts(Pageable pageable, String[] searchFor, String search);
}
