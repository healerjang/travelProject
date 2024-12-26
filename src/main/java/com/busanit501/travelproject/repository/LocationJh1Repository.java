package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationJh1Repository extends JpaRepository<Location, Long> {

}
