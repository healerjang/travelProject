package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.dto.freeboard.FreeBoardDTO;
import com.busanit501.travelproject.dto.freeboard.FreeBoardListReplyCountDTO;
import com.busanit501.travelproject.dto.freeboard.PageRequestDTO;
import com.busanit501.travelproject.dto.freeboard.PageResponseDTO;
import com.busanit501.travelproject.service.freeboard.FreeBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/free_board")
@RequiredArgsConstructor

public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model ) { // 서버 -> 화면으로 전달
        PageResponseDTO<FreeBoardListReplyCountDTO> responseDTO = freeBoardService.listWithReplyCount(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        model.addAttribute("responseDTO", responseDTO);

        return "freeboard/list";
    }

    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid FreeBoardDTO freeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + freeBoardDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/freeboard/list";
        }
        //검사가 통과가 되고, 정상 입력
        Long freeBoardNo = freeBoardService.register(freeBoardDTO);

        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/freeboard/list";

    }

    @GetMapping("/read")
    public void read(Long freeBoardNo, PageRequestDTO pageRequestDTO,
                     Model model) {
        FreeBoardDTO freeBoardDTO = freeBoardService.readOne(freeBoardNo);
        model.addAttribute("dto", freeBoardDTO);
    }

    @GetMapping("/update")
    public void update(Long freeBoardNo, PageRequestDTO pageRequestDTO,
                       Model model) {
        FreeBoardDTO freeBoardDTO = freeBoardService.readOne(freeBoardNo);
        model.addAttribute("dto", freeBoardDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid FreeBoardDTO freeBoardDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO,
                             String keyword2,String page2, String type2,
                             RedirectAttributes redirectAttributes) {
        log.info("FoodController updatePost post 로직처리: ");
        log.info("FoodController updatePost post  foodDTO : " + freeBoardDTO);

        log.info("FoodController updatePost post  pageRequestDTO : " + pageRequestDTO);


        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?freeBoardNo="+freeBoardDTO.getFreeBoardNo()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
        }

        freeBoardService.update(freeBoardDTO);

        redirectAttributes.addFlashAttribute("result", freeBoardDTO.getFreeBoardNo());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/food/read?freeBoardNo="+freeBoardDTO.getFreeBoardNo()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(Long freeBoardNo,
                         String keyword2,String page2, String type2,
                         RedirectAttributes redirectAttributes) {
        freeBoardService.delete(freeBoardNo);
        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/food/list?"+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
    }

}
