package com.busanit501.travelproject.service;

import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.service.manager.ManagerJh1Service;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class Jh1ServiceTest {

  @Autowired
  private ManagerJh1Service service;

  @Test
  public void registerLocationTest() {
    LocationValueJh1DTO dto = LocationValueJh1DTO.builder().country("베트남").city("다낭").build();
    Long id = service.registerLocation(dto);
  }

  @Test
  public void getLocationsTest() {
    List<LocationValueJh1DTO> dtoList = service.getLocationsOnly();
    dtoList.forEach(log::info);
  }

  @Test
  public void registerProductTest() {
    ProductJh1DTO dto = ProductJh1DTO.builder()
      .name("[오사카] 오사카 여행")
      .description("오사카 여행 설명이다")
      .price(550_000L)
      .locationNo(1L)
      .startDate(LocalDate.now().plusDays(40))
      .endDate(LocalDate.now().plusDays(45))
      .capacity(20)
      .imagePath("img.png")
      .build();
    Long pno = service.registerProduct(dto);
    log.info(pno);
  }
}
