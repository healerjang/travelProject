package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardJh1DTO {
  private Long freeBoardNo;
  private String title;
  private String content;
  private Long memberNo;

  /** 게시글 관리에서 사용될 회원 이름. null이 될 수도 있다. */
  private String memberName;
}
