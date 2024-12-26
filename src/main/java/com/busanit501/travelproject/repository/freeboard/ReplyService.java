package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
    // 부모 게시글 번호에 대한 댓글 목록 조회.
    PageResponseDTO<ReplyDTO> listWithReply(Long bno, PageRequestDTO pageRequestDTO);
}


