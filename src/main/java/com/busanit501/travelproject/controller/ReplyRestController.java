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

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Long>> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult
    ) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String,Long> map = Map.of("replyNo",1L);
        return ResponseEntity.ok(map);
    }


    @GetMapping(value ="/{replyNo}")
    public ReplyDTO getRead(@PathVariable("replyNo") Long replyNo)
    {
        ReplyDTO replyDTO = replyService.readOne(replyNo);
        return replyDTO;
    }


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


    @DeleteMapping(value ="/{replyNo}")
    public Map<String,Long> deleteReply(
            @PathVariable("replyNo") Long replyNo) throws BindException {
        replyService.delete(replyNo);
        Map<String,Long> map = Map.of("replyNo",replyNo);
        return map;
    }

    @GetMapping(value = "/count/{freeBoardNo}")
    public Long countReply(@PathVariable Long freeBoardNo){
        return replyService.countReply(freeBoardNo);
    }

    @GetMapping(value = "/replyList/{freeBoardNo}/{page}/{size}")
    public PageResponseDTO<ReplyDTO> getReplyList(@PathVariable Long freeBoardNo,
                                                  @PathVariable int page,
                                                  @PathVariable int size)
    {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(page,size);
        return replyService.page(pageRequestDTO, freeBoardNo);
    }


}





