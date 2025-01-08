package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import com.busanit501.travelproject.service.member.ResponseLogin;
import jakarta.servlet.http.HttpServletRequest;
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

  private void throwIfUnauthorized(MemberDTO memberDTO) {
    boolean admin = memberDTO.getResponseLogin() == ResponseLogin.ADMIN;
    if (!admin)
//      throw new UnauthorizedException("access denied");
      throw new RuntimeException("access denied");
  }


  @Member
  @GetMapping("/location/list")
  public String listLocation(
    HttpServletRequest request,
    MemberDTO memberDTO
  ) {
    throwIfUnauthorized(memberDTO);
    return "admin/listLocation_jh1";
  }


  @Member
  @GetMapping("/product/list")
  public String listProduct(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestJh1DTO
  ) {
    throwIfUnauthorized(memberDTO);
    return "admin/listProduct_jh1";
  }

  @Member
  @GetMapping("/product/register")
  public String registerProduct(
    HttpServletRequest request,
    MemberDTO memberDTO
  ) {
    throwIfUnauthorized(memberDTO);
    return "admin/registerProduct_jh1";
  }

  @Member
  @GetMapping("/product/edit/{productNo}")
  public String editProduct(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @PathVariable("productNo") @ModelAttribute("productNo") Long productNo,
    Model model
  ) {
    throwIfUnauthorized(memberDTO);
    ProductJh1DTO productDTO = adminService.getProductFull(productNo);
    model.addAttribute("productDTO", productDTO);
    return "admin/registerProduct_jh1";
  }

  @Member
  @GetMapping("/member/list")
  public String listMembers(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestDTO
  ) {
    throwIfUnauthorized(memberDTO);
    return "admin/listMembers_jh1";
  }

  @Member
  @GetMapping("/member/view/{id}")
  public String viewMember(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @PathVariable("id") Long id, Model model
  ) {
    throwIfUnauthorized(memberDTO);
    MemberFullDTO memberFullDTO = adminService.getMemberFullSupport(id);
    model.addAttribute("memberDTO", memberFullDTO);
    return "admin/viewMember_jh1";
  }

  @Member
  @GetMapping("/freeBoard/list")
  public String listFreeBoard(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @ModelAttribute("requestDTO") PageRequestJh1DTO pageRequestDTO
  ) {
    throwIfUnauthorized(memberDTO);
    return "admin/listFreeBoard_jh1";
  }
}
