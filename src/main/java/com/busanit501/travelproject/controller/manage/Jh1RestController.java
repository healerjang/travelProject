package com.busanit501.travelproject.controller.manage;

import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.service.manager.ManagerJh1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class Jh1RestController {

  private final ManagerJh1Service managerService;

  @GetMapping("/api/location/list")
  public ResponseEntity<List<LocationValueJh1DTO>> getLocationList() {
    List<LocationValueJh1DTO> dtoList = managerService.getLocationsOnly();
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  @PostMapping("/api/location")
  public ResponseEntity<Map<String, Object>> registerLocation(@RequestBody LocationValueJh1DTO dto) {
    Long locationNo = managerService.registerLocation(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "locationNo", locationNo));
  }

  @GetMapping("/api/product/{productNo}")
  public ResponseEntity<ProductJh1DTO> getProductById(@PathVariable("productNo") Long productNo) {
    ProductJh1DTO productDTO = managerService.getProductTmp(productNo);
    return ResponseEntity.ok().body(productDTO);
  }

  @PostMapping("/api/product")
  public ResponseEntity<Map<String, Object>> registerProduct(@RequestBody ProductJh1DTO dto) {
    Long productNo = managerService.registerProduct(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "productNo", productNo));
  }

}
