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
// http://localhost:8080/food, 시작하겠다.
public class FreeBoardController {
    private final FreeBoardService freeBoardService;
    // http://localhost:8080/food/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) { // 서버 -> 화면으로 전달
        // 서비스 이용해서, 데이터베이스 목록 페이징 처리해서 가져오기.
        // 앞단 화면에서, 검색어:keyword 내용, 페이징 내용(page = 1) 담아서 전달.
        //        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        // 교체 작업, 수정1 //기존 list 메서드를 -> 댓글개수를 포함한 메서드로 교체
        PageResponseDTO<FreeBoardListReplyCountDTO> responseDTO = freeBoardService.listWithReplyCount(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        model.addAttribute("responseDTO", responseDTO);
    }

    //등록 작업, 1) 등록화면 2) 로직처리
    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid FreeBoardDTO freeBoardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + freeBoardDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        //검사가 통과가 되고, 정상 입력
        Long freeBoardNo = freeBoardService.register(freeBoardDTO);
        // bno = 작성된 글의 번호를 받아와서 -> db 단에 foodDTO 게시글이 저장되면 저장된 게시글 넘버가 반환되어서 오는거임
        // db에 작성된 bno 를 가져옴 << 간단하게 정리

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
        redirectAttributes.addFlashAttribute("result", freeBoardNo);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/food/list";

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
        FreeBoardDTO freeBoardDTO = freeBoardService.readOne(freeBoardNo); //db에 데이터를 bno 로 조회된 내용을 가져왔다.
        model.addAttribute("dto", freeBoardDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid FreeBoardDTO freeBoardDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO, //업데이트에 post를 처리할 때는 get 방식이 아니기 때문에
                             // url 로 넘어와봤자 상관없음. 없어도됨. get으로 받을때
                             String keyword2,String page2, String type2,  //폼 안에 input 으로 받은거 서버단으로 넘어감
                             //차라리 dto 를 하나 더 만들면 되는거
                             RedirectAttributes redirectAttributes) {
        log.info("FoodController updatePost post 로직처리: ");
        log.info("FoodController updatePost post  foodDTO : " + freeBoardDTO);

        log.info("FoodController updatePost post  pageRequestDTO : " + pageRequestDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?freeBoardNo="+freeBoardDTO.getFreeBoardNo()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
        }
        //검사가 통과가 되고, 정상 입력
        freeBoardService.update(freeBoardDTO);

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
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
