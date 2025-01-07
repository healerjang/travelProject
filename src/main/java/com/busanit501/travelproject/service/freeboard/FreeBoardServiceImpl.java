package com.busanit501.travelproject.service.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.dto.freeboard.*;
import com.busanit501.travelproject.repository.freeboard.FreeBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FreeBoardServiceImpl implements com.busanit501.travelproject.service.freeboard.FreeBoardService {


    private final FreeBoardRepository freeBoardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(FreeBoardDTO freeBoardDTO) {
        FreeBoard freeBoard = FreeBoard.builder()
                .title(freeBoardDTO.getTitle())
                .content(freeBoardDTO.getContent())
                .member(Member.builder().memberNo(freeBoardDTO.getMemberNo()).build())
                .build();
                //modelMapper.map(freeBoardDTO, FreeBoard.class);
        // builder. << 객체를 만들기 위한거임
        // 그래서 FreeBoard << 엔티티 클래스를 들고와서 빌드업 패턴으로 객체로 만들어줌
        // 왜냐하면 다른 title content 는 전부 문자열로 들어오기 때문에 형태가 같음
        // 그치만 member 는 memberNo라는 long 으로 받아야 하는데 객체로 되어 있으니까
        Long freeBoardNo = freeBoardRepository.save(freeBoard).getFreeBoardNo();
        return freeBoardNo;
    }

    @Override
    public FreeBoardDTO readOne(Long freeBoardNo) {
        Optional<FreeBoard> result = freeBoardRepository.findById(freeBoardNo);
        FreeBoard freeBoard = result.orElseThrow();
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .freeBoardNo(freeBoardNo)
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .memberNo(freeBoard.getMember().getMemberNo())
                .memberName(freeBoard.getMember().getMemberName())
                .modDate(freeBoard.getMember().getModDate())
                .regDate(freeBoard.getMember().getRegDate())
                .build();
        return freeBoardDTO;
    }

    @Override
    public void update(FreeBoardDTO freeBoardDTO) {
        Optional<FreeBoard> result = freeBoardRepository.findById(freeBoardDTO.getFreeBoardNo());
        FreeBoard freeBoard = result.orElseThrow();
        freeBoard.changeTitleContent(freeBoardDTO.getTitle(), freeBoardDTO.getContent());
        freeBoardRepository.save(freeBoard);
    }

    @Override
    public void delete(Long freeBoardNo) {
        freeBoardRepository.deleteById(freeBoardNo);
    }

    @Override
    public PageResponseDTO<FreeBoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("freeBoaredNo");

        Page<FreeBoard> result = freeBoardRepository.searchAll(types, keyword, pageable);
        List<FreeBoardDTO> dtoList = result.getContent().stream()
                .map(freeBoard -> modelMapper.map(freeBoard, FreeBoardDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<FreeBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    }

    @Override
    public PageResponseDTO<FreeBoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("freeBoardNo");


        Page<FreeBoardListReplyCountDTO> result = freeBoardRepository.searchWithReplyCount(types,keyword,pageable);


        return PageResponseDTO.<FreeBoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<FreeBoardReadDTO> listReadWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("freeBoardNo");


        Page<FreeBoardReadDTO> result = freeBoardRepository.searchReadWithReplyCount(types,keyword,pageable);


        return PageResponseDTO.<FreeBoardReadDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }
}

