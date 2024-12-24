package com.busanit501.travelproject.service.manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationValueJh1DTO {
  private Long locationNo;
  private String country;
  private String city;
}
