package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.dto.productCgw.ProductDTO;
import com.busanit501.travelproject.dto.productCgw.ProductSearchRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import com.busanit501.travelproject.service.productCgw.ProductCgwServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/mainPage")
public class MainPageRestController {
    private final ProductCgwServiceImpl productCgwService;

    @GetMapping("/content")
    public PageResponseDTO<ProductDTO> getContent(ProductSearchRequestDTO productSearchRequestDTO) {
        return productCgwService.searchProduct(productSearchRequestDTO);
    }
}
