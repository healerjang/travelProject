package com.busanit501.travelproject.service.locationCgw;

import com.busanit501.travelproject.dto.locationCgw.LocationDTO;

import java.util.List;

public interface LocationService {

    List<String> getCountries();
    List<LocationDTO> getCities(String country);
}
