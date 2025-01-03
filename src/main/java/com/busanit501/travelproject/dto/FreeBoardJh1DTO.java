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
}
