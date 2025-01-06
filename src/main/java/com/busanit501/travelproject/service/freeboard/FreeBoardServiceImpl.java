package com.busanit501.travelproject.service.freeboard;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.dto.freeboard.FreeBoardDTO;
import com.busanit501.travelproject.dto.freeboard.FreeBoardListReplyCountDTO;
import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
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
        FreeBoard freeBoard = modelMapper.map(freeBoardDTO, FreeBoard.class);
        Long freeBoardNo = freeBoardRepository.save(freeBoard).getFreeBoardNo();
        return freeBoardNo;
    }

    @Override
    public FreeBoardDTO readOne(Long freeBoardNo) {
        Optional<FreeBoard> result = freeBoardRepository.findById(freeBoardNo);
        FreeBoard freeBoard = result.orElseThrow();
        FreeBoardDTO dto = modelMapper.map(freeBoard, FreeBoardDTO.class);
        return dto;
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
}

