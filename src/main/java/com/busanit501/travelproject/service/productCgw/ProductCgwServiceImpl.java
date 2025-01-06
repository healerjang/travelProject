package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.productCgw.ProductDTO;
import com.busanit501.travelproject.dto.productCgw.ProductSearchRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductCgw.ProductCgwRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductCgwServiceImpl implements ProductCgwService {

    private final ProductCgwRepository productCgwRepository;
    private final LocationJh1Repository locationJh1Repository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ProductDTO> searchProduct(ProductSearchRequestDTO productSearchRequestDTO) {
        Page<Product> products = productCgwRepository.searchProduct(locationJh1Repository.findById(productSearchRequestDTO.getLocationNo()).orElseThrow(), productSearchRequestDTO.getStartDate(), productSearchRequestDTO.getEndDate(), PageRequest.of(productSearchRequestDTO.getPage(), productSearchRequestDTO.getSize()));
        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(productSearchRequestDTO)
                .list(products.getContent().stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList())
                .build();
    }
}
