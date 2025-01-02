package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Reply;
import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final FreeBoardRepository freeBoardRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        // 화면에서 받은 데이터 DTO 타입 -> 엔티티 타입으로 변경,
        // replyDTO, bno 값이 존재.
        log.info("Registering new replyDTO: " + replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Optional<FreeBoard> result = freeBoardRepository.findById(replyDTO.getFreeBoardNo());
        FreeBoard freeBoard = result.orElseThrow();
        reply.changeBoard(freeBoard);
        log.info("Registering new reply: " + reply);
        Long replyNo = replyRepository.save(reply).getReplyNo();
        return replyNo;
    }

    @Override
    public ReplyDTO readOne(Long replyNo) {
        Optional<Reply> result = replyRepository.findById(replyNo);
        Reply reply = result.orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void update(ReplyDTO replyDTO) {
        Optional<Reply> result = replyRepository.findById(replyDTO.getReplyNo());
        Reply reply = result.orElseThrow();
        reply.changeReplyText(replyDTO.getReplyText());
        replyRepository.save(reply);
    }

    @Override
    public void delete(Long replyNo) {
        replyRepository.deleteById(replyNo);
    }

    @Override
    public PageResponseDTO<ReplyDTO> listWithReply(Long freeBoardNo, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1 <= 0 ? 0:pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(), Sort.by("replyNo").ascending());
        Page<Reply> result = replyRepository.listOfBoard(freeBoardNo, pageable);

        List<ReplyDTO>  dtoList = result.getContent().stream()
                .map(reply -> {
                    ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
                    replyDTO.setFreeBoardNo(reply.getFreeBoard().getFreeBoardNo());
                    return replyDTO;
                })
                .collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> pageResponseDTO = PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

        return pageResponseDTO;
    }
}
