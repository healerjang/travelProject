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

  /** 페이지 표시용 상품명. null이 될 수도 있음 */
  private String productName;



  private Long memberNo;

  /** 페이지 표시용 회원이름. null이 될 수도 있음 */
  private String memberName;

  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
