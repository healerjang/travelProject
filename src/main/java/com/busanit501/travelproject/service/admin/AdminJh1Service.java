package com.busanit501.travelproject.service.admin;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AdminJh1Service {
  @Transactional
  List<LocationValueJh1DTO> getLocationsOnly();

  Long registerLocation(LocationValueJh1DTO dto);

  @Transactional
  Long registerProduct(ProductJh1DTO dto);

  ProductJh1DTO getProductTmp(Long id);

  HcbPageResponseDTO<ProductJh1DTO> listProducts(HcbPageRequestDTO requestDTO);

  default ProductJh1DTO productEntityToDTO(Product product) {
    return ProductJh1DTO.builder()
      .productNo(product.getProductNo())
      .name(product.getName())
      .description(product.getDescription())
      .price(product.getPrice())
      .locationNo(product.getLocation().getLocationNo())
      .startDate(product.getStartDate())
      .endDate(product.getEndDate())
      .capacity(product.getCapacity())
      .imagePath(product.getImagePath())
      .build();
  }

}
