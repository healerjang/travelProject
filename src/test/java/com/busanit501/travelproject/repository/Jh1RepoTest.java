package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class Jh1RepoTest {

  @Autowired
  private LocationJh1Repository locRepo;

  @Test
  public void locOnlyTest() {
    List<LocationValueJh1DTO> locations = locRepo.listLocationsOnly();
    locations.forEach(log::info);
  }
}
