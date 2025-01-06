package com.busanit501.travelproject.controller.locationCgw;

import com.busanit501.travelproject.dto.locationCgw.LocationDTO;
import com.busanit501.travelproject.service.locationCgw.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationRestController {
    private final LocationService locationService;

    @GetMapping("/country")
    public List<String> getCountries() {
        return locationService.getCountries();
    }

    @GetMapping("/city/{country}")
    public List<LocationDTO> getCities(@PathVariable String country) {
        return locationService.getCities(country);
    }
}
