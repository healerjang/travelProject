package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.repository.locationCgw.LocationRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class LocationCgwRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void getCountriesTest() {
        log.info("LocationCgwRepositoryTest getCountriesTest result {}", locationRepository.getCountries());
    }

    @Test
    public void getCityTest() {
        List<String> Countries = locationRepository.getCountries();
        log.info("LocationCgwRepositoryTest getCityTest result");

        Countries.forEach(country -> log.info("result country : {} - cities {}", country, locationRepository.getCities(country)));
    }
}
