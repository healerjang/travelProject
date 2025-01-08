package com.busanit501.travelproject.repository.freeboard.search;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.dto.freeboard.FreeBoardListReplyCountDTO;
import com.busanit501.travelproject.dto.freeboard.FreeBoardReadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FreeBoardSearch {

    Page<FreeBoard> search(Pageable pageable);

    Page<FreeBoard> searchAll(String[] types, String keyword, Pageable pageable);

    Page<FreeBoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    Page<FreeBoardReadDTO> searchReadWithReplyCount(String[] types, String keyword, Pageable pageable);
}
