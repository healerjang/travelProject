package com.busanit501.travelproject.dto.freeboard;

import com.busanit501.travelproject.dto.ReplyJh1DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 관리자 페이지에서 사용할 의도로 만들었던 자유게시판 목록 및 상세조회 DTO
 */
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

  /** 댓글 목록. 이 게시글 하나만 조회할 때에만 사용 가능. */
  private List<ReplyJh1DTO> replies;

  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
