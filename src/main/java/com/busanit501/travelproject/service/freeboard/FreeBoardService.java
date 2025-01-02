package com.busanit501.travelproject.service.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.dto.freeboard.FreeBoardDTO;
import com.busanit501.travelproject.dto.freeboard.FreeBoardListReplyCountDTO;
import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface FreeBoardService {
    Long register(FreeBoardDTO freeBoardDTO); //Long 반환타입
    FreeBoardDTO readOne(Long freeBoardNo);
    void update(FreeBoardDTO freeBoardDTO);
    void delete(Long freeBoardNo);
    PageResponseDTO<FreeBoardDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<FreeBoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);



}
