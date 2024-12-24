package com.busanit501.travelproject.controller.manage;

import com.busanit501.travelproject.repository.LocationJh1Repository;
import com.busanit501.travelproject.service.manager.LocationValueJh1DTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Jh1RestController {

  private final LocationJh1Repository locRepo;

  @GetMapping("/api/location/list")
  public ResponseEntity<List<LocationValueJh1DTO>> getLocationList() {
    List<LocationValueJh1DTO> dtoList = locRepo.listLocationsOnly();
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

}
