package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.ProductJh1DTO;

public interface MemberProductDetailService {
    ProductJh1DTO getProductDetail(Long productNo);

    ProductJh1DTO getProductById(Long productNo);
}

