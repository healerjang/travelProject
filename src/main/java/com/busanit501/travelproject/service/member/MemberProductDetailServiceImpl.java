package com.busanit501.travelproject.service.member;

import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProductDetailServiceImpl implements MemberProductDetailService {

    private final AdminJh1Service adminJh1Service;

    @Override
    public ProductJh1DTO getProductDetail(Long productNo) {
        return adminJh1Service.getProductFull(productNo);
    }

    @Override
    public ProductJh1DTO getProductById(Long productNo) {
        return getProductDetail(productNo);  // 기존의 getProductDetail을 활용하여 기본 정보를 반환
    }
}
