package com.busanit501.travelproject.repository.locationCgw;

import com.busanit501.travelproject.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>, CustomLocation {
}
