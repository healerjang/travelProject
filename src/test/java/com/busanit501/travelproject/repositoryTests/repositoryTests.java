package com.busanit501.travelproject.repositoryTests;

import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Reply;
import com.busanit501.travelproject.repository.freeboard.FreeBoardRepository;
import com.busanit501.travelproject.repository.freeboard.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@Log4j2
@Transactional
public class repositoryTests {
        @Autowired
        private ReplyRepository replyRepository;
        @Autowired
        private FreeBoardRepository freeBoardRepository;

        @Test
    public void countTest() {
            FreeBoard freeBoard = freeBoardRepository.findById(3L).orElseThrow();
            Long CountFreeBoardNo = replyRepository.countByFreeBoard(freeBoard);
            log.info("reply 갯수: " + CountFreeBoardNo);


    }

    @Test
    public void findByFreeBoard(){
            FreeBoard freeBoard = freeBoardRepository.findById(3L).orElseThrow();
        Page<Reply> replies = replyRepository.
                findByFreeBoard(freeBoard, PageRequest.of(0, 10));
        log.info("replies" + replies.getContent());

    }






}
