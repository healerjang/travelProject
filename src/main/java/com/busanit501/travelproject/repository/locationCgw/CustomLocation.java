package com.busanit501.travelproject.repository.locationCgw;

import com.busanit501.travelproject.dto.locationCgw.LocationDTO;

import java.util.List;

public interface CustomLocation {
    List<String> getCountries();
    List<LocationDTO> getCities(String country);
}
