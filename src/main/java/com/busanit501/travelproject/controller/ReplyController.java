package com.busanit501.travelproject.controller;



import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import com.busanit501.travelproject.repository.freeboard.ReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Tag(name = "댓글 등록 post 방식",
            description = "댓글 등록을 진행함, post 형식으로")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Long>> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult
    ) throws BindException {
        log.info(" ReplyController replyDTO: ", replyDTO);
        // 확인용, 더미 데이터 ,

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String,Long> map = Map.of("replyNo",100L);
        return ResponseEntity.ok(map);
    }

    @Tag(name = "댓글 목록 조회",description = "댓글 목록 조회 RESTful get방식")
    @GetMapping(value ="/list/{replyNo}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("replyNo") Long replyNo, PageRequestDTO pageRequestDTO)
    {
        log.info(" ReplyController getList: replyNo={}", replyNo);
        PageResponseDTO<ReplyDTO> responseDTO = replyService.listWithReply(replyNo, pageRequestDTO);
        return responseDTO;
    }


    @Tag(name = "댓글 하나 조회",description = "댓글 하나 조회 RESTful get방식")
    @GetMapping(value ="/{replyNo}")
    public ReplyDTO getRead(@PathVariable("replyNo") Long replyNo)
    {
        log.info(" ReplyController getRead: rno={}", replyNo);
        ReplyDTO replyDTO = replyService.readOne(replyNo);
        return replyDTO;
    }

    // 댓글 수정, 로직처리
    // localhost:8080/replies/{rno:댓글번호}
    @Tag(name = "댓글 수정 로직처리",description = "댓글 수정 로직처리 RESTful get방식")
    @PutMapping(value ="/{replyNo}")
    public Map<String,Long> updateReply(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult,
            @PathVariable("replyNo") Long replyNo) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        log.info(" ReplyController updateReply: replyDTO={}", replyDTO);
        log.info(" ReplyController updateReply: rno={}", replyNo);
        replyService.update(replyDTO);
        Map<String,Long> map = Map.of("rno",replyNo);
        return map;
    }


    @Tag(name = "댓글 삭제 로직처리",description = "댓글 삭제 로직처리 RESTful get방식")
    @DeleteMapping(value ="/{replyNo}")
    public Map<String,Long> deleteReply(
            @PathVariable("replyNo") Long replyNo) throws BindException {
        replyService.delete(replyNo);
        Map<String,Long> map = Map.of("replyNo",replyNo);
        return map;
    }

}





