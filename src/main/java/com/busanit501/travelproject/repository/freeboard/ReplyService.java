package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);

    PageResponseDTO<ReplyDTO> listWithReply(Long bno, PageRequestDTO pageRequestDTO);
}


