package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewJh1DTO {
  private Long reviewNo;
  private String reviewContent;
  private int rating;
  private Long productNo;
  private Long memberNo;
}
