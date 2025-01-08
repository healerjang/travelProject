package com.busanit501.travelproject.service.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.dto.freeboard.*;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface FreeBoardService {
    Long register(FreeBoardDTO freeBoardDTO); //Long 반환타입
    FreeBoardDTO readOne(Long freeBoardNo);
    void update(FreeBoardDTO freeBoardDTO);
    void delete(Long freeBoardNo);
    PageResponseDTO<FreeBoardDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<FreeBoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);


    PageResponseDTO<FreeBoardReadDTO> listReadWithReplyCount(PageRequestDTO pageRequestDTO);
}
