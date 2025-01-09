package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import com.busanit501.travelproject.dto.util.PageRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long replyNo);
    void update(ReplyDTO replyDTO);
    void delete(Long replyDTO);
    Long countReply(Long freeBoardNo);
    PageResponseDTO<ReplyDTO> page(PageRequestDTO requestDTO, Long freeBoardNo);
}


