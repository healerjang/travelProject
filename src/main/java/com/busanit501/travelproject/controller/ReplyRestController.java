package com.busanit501.travelproject.controller;



import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import com.busanit501.travelproject.dto.util.PageRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import com.busanit501.travelproject.repository.freeboard.ReplyService;
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
public class ReplyRestController {

    private final ReplyService replyService;

    @PostMapping(value = "/write", consumes = MediaType.APPLICATION_JSON_VALUE)

    public Long register(
            @RequestBody ReplyDTO replyDTO
    ) throws BindException {
        return replyService.register(replyDTO);
    }

// 댓글 조회
    @GetMapping(value ="/{replyNo}")
    public ReplyDTO getRead(@PathVariable("replyNo") Long replyNo)
    {
        ReplyDTO replyDTO = replyService.readOne(replyNo);
        return replyDTO;
    }

// 댓글 수정
    @PutMapping(value ="/{replyNo}")
    public Map<String,Long> updateReply(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult,
            @PathVariable("replyNo") Long replyNo) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        replyService.update(replyDTO);
        Map<String,Long> map = Map.of("replyNo",replyNo);
        return map;
    }

// 댓글 삭제
    @DeleteMapping(value ="/{replyNo}")
    public Map<String,Long> deleteReply(
            @PathVariable("replyNo") Long replyNo) throws BindException {
        replyService.delete(replyNo);
        Map<String,Long> map = Map.of("replyNo",replyNo);
        return map;
    }

    // 댓글 카운트
    @GetMapping(value = "/count/{freeBoardNo}")
    public Long countReply(@PathVariable Long freeBoardNo){
        return replyService.countReply(freeBoardNo);
    }

    // 댓글 리스트
    @GetMapping(value = "/replyList/{freeBoardNo}/{page}/{size}")
    public PageResponseDTO<ReplyDTO> getReplyList(@PathVariable Long freeBoardNo,
                                                  @PathVariable int page,
                                                  @PathVariable int size)
    {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(page,size);
        return replyService.page(pageRequestDTO, freeBoardNo);
    }


}





