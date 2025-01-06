package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.productCgw.ProductDTO;
import com.busanit501.travelproject.dto.productCgw.ProductSearchRequestDTO;
import com.busanit501.travelproject.dto.util.PageRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.repository.ProductCgw.ProductCgwRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductCgwServiceImpl implements ProductCgwService {

    private final ProductCgwRepository productCgwRepository;
    private final LocationJh1Repository locationJh1Repository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ProductDTO> searchProduct(ProductSearchRequestDTO productSearchRequestDTO) {
        if (productSearchRequestDTO.isData()) {
            Page<Product> products = productCgwRepository.searchProduct(locationJh1Repository.findById(productSearchRequestDTO.getLocationNo()).orElse(null), productSearchRequestDTO.getStartDate(), productSearchRequestDTO.getEndDate(), PageRequest.of(productSearchRequestDTO.getPage() - 1, productSearchRequestDTO.getSize()));
            return PageResponseDTO.<ProductDTO>builder()
                    .pageRequestDTO(productSearchRequestDTO)
                    .list(products.getContent().stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList())
                    .total(products.getTotalElements())
                    .build();
        }
        List<ProductDTO> productDTOList = reservationRepository.bestReservationProducts().stream().map(product -> modelMapper.map(productCgwRepository.findById(product).orElseThrow(), ProductDTO.class)).toList();

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(new PageRequestDTO(1, 10))
                .list(productDTOList)
                .total(10)
                .build();
    }
}
