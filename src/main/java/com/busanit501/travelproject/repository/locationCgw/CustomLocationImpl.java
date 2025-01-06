package com.busanit501.travelproject.repository.locationCgw;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.QLocation;
import com.busanit501.travelproject.dto.locationCgw.LocationDTO;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CustomLocationImpl extends QuerydslRepositorySupport implements CustomLocation {
    public CustomLocationImpl() {
        super(Location.class);
    }

    @Override
    public List<String> getCountries() {
        QLocation location = QLocation.location;
        JPQLQuery<String> query = from(location).select(location.country).distinct();
        return query.fetch();
    }

    @Override
    public List<LocationDTO> getCities(String country) {
        QLocation location = QLocation.location;
        JPQLQuery<Location> query = from(location).where(location.country.eq(country)).groupBy(location.city);
        return query.fetch().stream().map(result -> LocationDTO.builder()
                .locationNo(result.getLocationNo())
                .country(result.getCountry())
                .city(result.getCity())
                .build()).toList();
    }
}
