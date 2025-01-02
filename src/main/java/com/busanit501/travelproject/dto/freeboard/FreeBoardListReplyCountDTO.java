package com.busanit501.travelproject.dto.freeboard;

import lombok.Data;

import java.time.LocalDateTime;

// 화면 목록에, 댓글의 갯수를 나타내기위한 박스
@Data
public class FreeBoardListReplyCountDTO {
    private  Long freeBoardNo;
    private  String title;
    private  String content;
    private  String member;
    private LocalDateTime regDate;

    // 댓글의 갯수
    private Long replyCount;
}
