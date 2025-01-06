package com.busanit501.travelproject.repository.ProductCgw;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ProductCgwSearch {
    Page<Product> searchProduct(Location location, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
