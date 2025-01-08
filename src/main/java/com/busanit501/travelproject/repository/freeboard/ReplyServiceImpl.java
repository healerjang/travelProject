package com.busanit501.travelproject.repository.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Reply;
import com.busanit501.travelproject.dto.freeboard.ReplyDTO;
import com.busanit501.travelproject.dto.util.PageRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        Reply reply = modelMapper.map(replyDTO, Reply.class);
        // null 일수도 있고 아닐수도 있기 때문에 optional 넣는거임
        Optional<FreeBoard> result = freeBoardRepository.findById(replyDTO.getFreeBoardNo());
        FreeBoard freeBoard = result.orElseThrow();
        reply.changeBoard(freeBoard);
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
    public Long countReply(Long freeBoardNo) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardNo).orElseThrow();
        return replyRepository.countByFreeBoard(freeBoard);
    }

    // repository 에서 pageReply 를 들고와서 responseDTO로 매핑하는과정임
    @Override
    public PageResponseDTO<ReplyDTO> page(PageRequestDTO requestDTO, Long freeBoardNo) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardNo).orElseThrow();
        Page<Reply> replies = replyRepository.
                findByFreeBoard(freeBoard, PageRequest.of
                        (requestDTO.getPage()-1, requestDTO.getSize()));
        return PageResponseDTO.<ReplyDTO>builder()
                .list(replies.getContent().stream().map(reply -> {
                    return modelMapper.map(reply, ReplyDTO.class);
                }).toList())
                .total(replies.getTotalElements())
                .pageRequestDTO(requestDTO)
                .build();
    }
}
