package com.busanit501.travelproject.service.manager;

import jakarta.transaction.Transactional;

import java.util.List;

public interface ManagerJh1Service {
  @Transactional
  List<LocationValueJh1DTO> getLocationsOnly();

  Long registerLocation(LocationValueJh1DTO dto);

  @Transactional
  Long registerProduct(ProductJh1DTO dto);
}
