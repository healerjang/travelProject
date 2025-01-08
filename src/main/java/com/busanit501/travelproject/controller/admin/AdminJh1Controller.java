package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminJh1Controller {

  private final AdminJh1Service adminService;



  @GetMapping("/location/list")
  public String listLocation() {
    return "admin/listLocation_jh1";
  }



  @GetMapping("/product/list")
  public String listProduct(
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestJh1DTO
  ) {
    return "admin/listProduct_jh1";
  }

  @GetMapping("/product/register")
  public String registerProduct() {
    return "admin/registerProduct_jh1";
  }

  @GetMapping("/product/edit/{productNo}")
  public String editProduct(@PathVariable("productNo") @ModelAttribute("productNo") Long productNo, Model model) {
    ProductJh1DTO productDTO = adminService.getProductFull(productNo);
    log.info("productDTO: {}", productDTO);
    model.addAttribute("productDTO", productDTO);
    return "admin/registerProduct_jh1";
  }



  @GetMapping("/member/list")
  public String listMembers(
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestDTO
  ) {
    return "admin/listMembers_jh1";
  }

  @GetMapping("/member/view/{id}")
  public String viewMember(@PathVariable("id") Long id, Model model) {
    MemberFullDTO memberDTO = adminService.getMemberFullSupport(id);
    log.info("MemberFullDTO: {}", memberDTO);
    model.addAttribute("memberDTO", memberDTO);
    return "admin/viewMember_jh1";
  }

  @GetMapping("/freeBoard/list")
  public String listFreeBoard(
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestDTO
  ) {
    return "admin/listFreeBoard_jh1";
  }
}
