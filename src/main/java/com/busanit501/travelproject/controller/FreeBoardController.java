package com.busanit501.travelproject.controller;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.freeboard.*;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.exception.member.UnauthorizedException;
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

    @Member
    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model, MemberDTO memberDTO, HttpServletRequest request ){ // 서버 -> 화면으로 전달
        PageResponseDTO<FreeBoardReadDTO> responseDTO = freeBoardService.listReadWithReplyCount(pageRequestDTO);
        Long memberNo = memberDTO != null ? memberDTO.getMemberNo() : 0;
        model.addAttribute("responseDTO", responseDTO);
        if (memberDTO != null) {
            model.addAttribute("member", true);
        }
        model.addAttribute("memberNo", memberNo);
        return "free_board/list";
    }

    @Member
    @GetMapping("/register")
    public void register(MemberDTO memberDTO, HttpServletRequest request, Model model) throws UnauthorizedException {
        if (memberDTO != null) model.addAttribute("member", true);
        else throw new UnauthorizedException("return");
    }

    @Member
    @PostMapping("/register")
    public String registerPost(
            HttpServletRequest request,
            MemberDTO memberDTO,
            @Valid FreeBoardDTO freeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) throws UnauthorizedException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/free_board/list";
        }
        if (memberDTO == null) throw new UnauthorizedException("return");
        freeBoardDTO.setMemberNo(memberDTO.getMemberNo());

        Long freeBoardNo = freeBoardService.register(freeBoardDTO);

        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/free_board/list";

    }

    @GetMapping("/read")
    @Member
    // httpServletRequest request요청해서 @Member<< 들어있는 MemberDTO 그대로 주입하겠다 그런의미
    public void read(Long freeBoardNo, PageRequestDTO pageRequestDTO, HttpServletRequest request, MemberDTO memberDTO,
                     Model model) {
        Long memberNo = memberDTO != null ? memberDTO.getMemberNo() : 0;
        model.addAttribute("memberNo", memberNo);

        if (memberDTO != null) {
            model.addAttribute("member", true);
            model.addAttribute("memberName", memberDTO.getMemberName());
        }
        FreeBoardDTO freeBoardDTO = freeBoardService.readOne(freeBoardNo);
        model.addAttribute("dto", freeBoardDTO);
    }

    @Member
    @GetMapping("/update")
    public void update(Long freeBoardNo, PageRequestDTO pageRequestDTO, MemberDTO memberDTO,
                       Model model) {
        if (memberDTO != null) {model.addAttribute("member", true);}
        FreeBoardDTO freeBoardDTO = freeBoardService.readOne(freeBoardNo);
        model.addAttribute("dto", freeBoardDTO);
    }

    @Member
    @PostMapping("/update")
    public String updatePost(@Valid FreeBoardDTO freeBoardDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO,
                             MemberDTO memberDTO,
                             HttpServletRequest request,
                             String keyword2,String page2, String type2,
                             RedirectAttributes redirectAttributes) throws UnauthorizedException {

        if (memberDTO == null || memberDTO.getMemberNo() != freeBoardDTO.getMemberNo()) throw new UnauthorizedException("return");

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

    @Member
    @PostMapping("/delete")
    public String delete(Long freeBoardNo, RedirectAttributes redirectAttributes, MemberDTO memberDTO, HttpServletRequest request) throws UnauthorizedException {
        if (memberDTO == null || memberDTO.getMemberNo() != freeBoardService.readOne(freeBoardNo).getMemberNo()) throw new UnauthorizedException("return");

        freeBoardService.delete(freeBoardNo);

        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "delete");

        return "redirect:/free_board/list";
    }

    }

