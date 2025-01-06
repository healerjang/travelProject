package com.busanit501.travelproject.dto.locationCgw;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LocationDTO {
    private long locationNo;
    private String country;
    private String city;
}
