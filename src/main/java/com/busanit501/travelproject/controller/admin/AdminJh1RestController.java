package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.dto.freeboard.FreeBoardJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.ProductUpdateJh1DTO;
import com.busanit501.travelproject.dto.member.MemberAddPointDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.dto.util.PageResponseJh1DTO;
import com.busanit501.travelproject.exception.member.UnauthorizedRestException;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import com.busanit501.travelproject.service.member.ResponseLogin;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminJh1RestController {

  private final AdminJh1Service adminService;

  @SneakyThrows
  private void throwIfUnauthorized(MemberDTO memberDTO) {
    if (memberDTO == null)
      throw new UnauthorizedRestException("access denied");
    boolean admin = memberDTO.getResponseLogin() == ResponseLogin.ADMIN;
    if (!admin)
      throw new UnauthorizedRestException("access denied");
//      throw new RuntimeException("access denied");
  }

  @Member
  @GetMapping("/location/list")
  public ResponseEntity<List<LocationValueJh1DTO>> getLocationList(
    HttpServletRequest request,
    MemberDTO memberDTO
  ) {
    throwIfUnauthorized(memberDTO);
    List<LocationValueJh1DTO> dtoList = adminService.getLocationsOnly();
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  @Member
  @PostMapping("/location")
  public ResponseEntity<Map<String, Object>> registerLocation(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @RequestBody LocationValueJh1DTO dto
  ) {
    throwIfUnauthorized(memberDTO);
    Long locationNo = adminService.registerLocation(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "locationNo", locationNo));
  }

  @Member
  @GetMapping("/product/{productNo}")
  public ResponseEntity<ProductJh1DTO> getProductById(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @PathVariable("productNo") Long productNo
  ) {
    throwIfUnauthorized(memberDTO);
    ProductJh1DTO productDTO = adminService.getProductCompact(productNo);
    return ResponseEntity.ok().body(productDTO);
  }

  @Member
  @GetMapping("/product/list")
  public ResponseEntity<PageResponseJh1DTO<ProductJh1DTO>> getProductList(
    HttpServletRequest request,
    MemberDTO memberDTO,
    PageRequestJh1DTO requestDTO
  ) {
    throwIfUnauthorized(memberDTO);
    PageResponseJh1DTO<ProductJh1DTO> pageResponseDTO = adminService.listProducts(requestDTO);
    return ResponseEntity.ok().body(pageResponseDTO);
  }

  @Member
  @PostMapping("/product")
  public ResponseEntity<Map<String, Object>> registerProduct(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @RequestBody ProductJh1DTO dto
  ) {
    throwIfUnauthorized(memberDTO);
    Long productNo = adminService.registerProduct(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "productNo", productNo));
  }

  @Member
  @PutMapping("/product")
  public ResponseEntity<Map<String, Object>> updateProduct(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @RequestBody ProductUpdateJh1DTO dto
  ) {
    throwIfUnauthorized(memberDTO);
    adminService.updateProduct(dto);
    return ResponseEntity.ok().body(Map.of("success", true));
  }


  @Member
  @GetMapping("/member/list")
  public ResponseEntity<PageResponseJh1DTO<MemberDTO>> listMembers(
    HttpServletRequest request,
    MemberDTO memberDTO,
    PageRequestJh1DTO requestDTO
  ) {
    throwIfUnauthorized(memberDTO);
    PageResponseJh1DTO<MemberDTO> membersDTO = adminService.listMembers(requestDTO);
    return ResponseEntity.ok().body(membersDTO);
  }

  @Member
  @PostMapping("/member/givePoint")
  public ResponseEntity<Map<String, Object>> givePoint(
    HttpServletRequest request,
    MemberDTO memberDTO,
    @RequestBody MemberAddPointDTO dto
  ) {
    throwIfUnauthorized(memberDTO);
    adminService.givePointTo(dto.getMemberNo(), dto.getAmount());
    return ResponseEntity.ok().body(Map.of("success", true));
  }


  @Member
  @GetMapping("/freeBoard/list")
  public ResponseEntity<PageResponseJh1DTO<FreeBoardJh1DTO>> listBoards(
    HttpServletRequest request,
    MemberDTO memberDTO,
    PageRequestJh1DTO requestDTO
  ) {
    throwIfUnauthorized(memberDTO);
    PageResponseJh1DTO<FreeBoardJh1DTO> freeBoardList = adminService.getFreeBoardList(requestDTO);
    return ResponseEntity.ok().body(freeBoardList);
  }
}
