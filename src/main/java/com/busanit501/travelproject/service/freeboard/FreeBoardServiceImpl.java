package com.busanit501.travelproject.service.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.dto.freeboard.*;
import com.busanit501.travelproject.repository.freeboard.FreeBoardRepository;
import com.busanit501.travelproject.repository.member.MemberRepository;
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

    // memberRepository 추가
    private final MemberRepository memberRepository;

    @Override
    public Long register(FreeBoardDTO freeBoardDTO) {
        FreeBoard freeBoard = FreeBoard.builder()
                .title(freeBoardDTO.getTitle())
                .content(freeBoardDTO.getContent())
                .member(memberRepository.findById(freeBoardDTO.getMemberNo()).orElse(null))
                .build();
                //modelMapper.map(freeBoardDTO, FreeBoard.class);
        // builder. << 객체를 만들기 위한거임
        // 그래서 FreeBoard << 엔티티 클래스를 들고와서 빌드업 패턴으로 객체로 만들어줌
        // 왜냐하면 다른 title content 는 전부 문자열로 들어오기 때문에 형태가 같음
        // 그치만 member 는 memberNo라는 long 으로 받아야 하는데 객체로 되어 있으니까
        
        // 주석 추가 - 기존의 .member(member.Builder.memberNo(freeBoardDTO.getMemberNo()).build)의 방식은 불안정.
        // member가 엔티티 클래스 이기에 jpa에서 데이터 베이스에 있는 memberNo가 있을 경우 로직 상으로는 큰 문제는 없으나 만약 데이터 베이스 내 memberNo가 없을 경우 SQLSyntaxErrorException이 날 거임.
        // 그렇기에 member는 memberRepository로 하여금 Optional로 객체를 가져와 예외 처리를 해주는 게 안전
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
                .modDate(freeBoard.getModDate())
                .regDate(freeBoard.getRegDate())
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

