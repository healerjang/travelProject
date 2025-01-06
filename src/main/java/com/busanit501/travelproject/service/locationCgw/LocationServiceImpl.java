package com.busanit501.travelproject.service.locationCgw;

import com.busanit501.travelproject.dto.locationCgw.LocationDTO;
import com.busanit501.travelproject.repository.locationCgw.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public List<String> getCountries() {return locationRepository.getCountries();}

    @Override
    public List<LocationDTO> getCities(String country) {return locationRepository.getCities(country);}
}
