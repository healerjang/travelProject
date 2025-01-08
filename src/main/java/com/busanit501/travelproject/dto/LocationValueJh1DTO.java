package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 해당 여행지와 연관된 상품은 모르겠고, 여행지 정보만 담겨 있는 DTO
 * @author 원종호
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationValueJh1DTO {
  private Long locationNo;
  private String country;
  private String city;
}
