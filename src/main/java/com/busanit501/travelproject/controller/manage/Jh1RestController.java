package com.busanit501.travelproject.controller.manage;

import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.service.manager.ManagerJh1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Jh1RestController {

  private final ManagerJh1Service managerService;

  @GetMapping("/api/location/list")
  public ResponseEntity<List<LocationValueJh1DTO>> getLocationList() {
    List<LocationValueJh1DTO> dtoList = managerService.getLocationsOnly();
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  @GetMapping("/api/test/product/1")
  public ResponseEntity<ProductJh1DTO> dtoJsonTest() {
    ProductJh1DTO dto = managerService.getProductTmp(1L);
    return ResponseEntity.ok().body(dto);
  }

}
