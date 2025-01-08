package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.freeboard.*;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.service.freeboard.FreeBoardService;
import jakarta.servlet.http.HttpServletRequest;
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
        PageResponseDTO<FreeBoardReadDTO> responseDTO = freeBoardService.listReadWithReplyCount(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "free_board/list";
    }

    @GetMapping("/register")
    public void register() {

    }

    @Member
    @PostMapping("/register")
    public String registerPost(
            HttpServletRequest request,
            MemberDTO memberDTO,
            @Valid FreeBoardDTO freeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/free_board/list";
        }
        freeBoardDTO.setMemberNo(memberDTO.getMemberNo());

        Long freeBoardNo = freeBoardService.register(freeBoardDTO);

        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/free_board/list";

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

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?freeBoardNo="+freeBoardDTO.getFreeBoardNo()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
        }

        freeBoardService.update(freeBoardDTO);

        redirectAttributes.addFlashAttribute("result", freeBoardDTO.getFreeBoardNo());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/free_board/read?freeBoardNo="+freeBoardDTO.getFreeBoardNo()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(Long freeBoardNo, RedirectAttributes redirectAttributes) {
        freeBoardService.delete(freeBoardNo);

        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "delete");

        return "redirect:/free_board/list";
    }

    }

