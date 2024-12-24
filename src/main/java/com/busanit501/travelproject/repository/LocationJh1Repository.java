package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.service.manager.LocationValueJh1DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationJh1Repository extends JpaRepository<Location, Long> {

  @Query("select new com.busanit501.travelproject.service.manager.LocationValueJh1DTO(loc.locationNo, loc.country, loc.city)  from Location loc")
  List<LocationValueJh1DTO> listLocationsOnly();

}
