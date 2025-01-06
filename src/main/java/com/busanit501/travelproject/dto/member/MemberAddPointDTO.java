package com.busanit501.travelproject.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원에게 포인트를 지급하기 위한 DTO
 * @author 원종호
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddPointDTO {
  private long memberNo;
  private int amount;
}
