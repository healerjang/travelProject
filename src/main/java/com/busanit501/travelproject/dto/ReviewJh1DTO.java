package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Admin에서 쓰려고 만든 리뷰 표시용 DTO
 * @author 원종호
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewJh1DTO {
  private Long reviewNo;
  private String reviewContent;
  private int rating;
  private Long productNo;
  private String productName;
  private Long memberNo;

  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
