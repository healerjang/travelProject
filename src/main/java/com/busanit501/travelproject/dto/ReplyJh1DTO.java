package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 *   관리 페이지에서 사용할 목적으로 만들었던 DTO
 * </p>
 * <p>
 *   회원 페이지에서 회원 작성 댓글 목록을 볼 떄에는 <code>memberNo</code>, <code>memberName</code>이 null이 되고,<br/>
 *   자유게시판 페이지에서 게시글 댓글 목록을 볼 때에는 <code>freeBoardNo</code>, <code>freeBoardTitle</code>이 null이 된다.
 * </p>
 * @author 원종호
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyJh1DTO {
  private Long replyNo;
  private String replyText;

  private Long memberNo;
  private String memberName;

  private Long freeBoardNo;
  private String freeBoardTitle;

  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
