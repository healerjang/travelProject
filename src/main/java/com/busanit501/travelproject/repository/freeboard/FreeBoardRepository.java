package com.busanit501.travelproject.repository.freeboard;

import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.repository.freeboard.search.FreeBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> , FreeBoardSearch {


    Page<FreeBoard> findByTitleContainingOrderByFreeBoardNoDesc(String title, Pageable pageable);


    @Query("select b from FreeBoard b where b.title like concat('%',:keyword,'%')")
    Page<FreeBoard> findByKeyword(String keyword, Pageable pageable);


    @Query(value = "select now()" , nativeQuery = true)
    String now();




}
