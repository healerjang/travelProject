package com.busanit501.travelproject.dto;

import lombok.*;

/**
 * 이미지 관리를 위한 DTO
 * 어디까지나 "이미지 관리"가 주 목적이므로 진짜 필요한 것만 담았다.
 * @author 원종호
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageAdminDTO {
  private Long productNo;
  private String productName;
  private String imagePath;
}
