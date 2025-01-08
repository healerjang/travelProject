package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.freeBoard.freeBoardNo = :freeBoardNo")
    Page<Reply> listOfBoard(Long freeBoardNo, Pageable pageable);
    Long countByFreeBoard(FreeBoard freeBoard);
    Page<Reply> findByFreeBoard(FreeBoard freeBoard, Pageable pageable);


}
