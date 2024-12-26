package com.busanit501.travelproject.service.manager;

import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ManagerJh1Service {
  @Transactional
  List<LocationValueJh1DTO> getLocationsOnly();

  Long registerLocation(LocationValueJh1DTO dto);

  @Transactional
  Long registerProduct(ProductJh1DTO dto);

  ProductJh1DTO getProductTmp(Long id);
}
