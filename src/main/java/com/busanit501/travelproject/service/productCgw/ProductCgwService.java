package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.dto.productCgw.ProductDTO;
import com.busanit501.travelproject.dto.productCgw.ProductSearchRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;

public interface ProductCgwService {
    PageResponseDTO<ProductDTO> searchProduct(ProductSearchRequestDTO productSearchRequestDTO);
}
