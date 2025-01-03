package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyJh1DTO {
  private Long replyNo;
  private String content;
  private Long memberNo;

  /* Reply 엔티티에는 없지만 이후 추가될 때를 대비하여 */
  //  private Long freeBoardNo;
}
