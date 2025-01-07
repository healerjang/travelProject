package com.busanit501.travelproject.controller.admin;

import com.busanit501.travelproject.dto.FreeBoardJh1DTO;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.LocationValueJh1DTO;
import com.busanit501.travelproject.dto.member.MemberAddPointDTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.dto.util.PageResponseJh1DTO;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/location/list")
  public ResponseEntity<List<LocationValueJh1DTO>> getLocationList() {
    List<LocationValueJh1DTO> dtoList = adminService.getLocationsOnly();
    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  @PostMapping("/location")
  public ResponseEntity<Map<String, Object>> registerLocation(@RequestBody LocationValueJh1DTO dto) {
    Long locationNo = adminService.registerLocation(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "locationNo", locationNo));
  }

  @GetMapping("/product/{productNo}")
  public ResponseEntity<ProductJh1DTO> getProductById(@PathVariable("productNo") Long productNo) {
    ProductJh1DTO productDTO = adminService.getProductTmp(productNo);
    return ResponseEntity.ok().body(productDTO);
  }

  @GetMapping("/product/list")
  public ResponseEntity<PageResponseJh1DTO<ProductJh1DTO>> getProductList(
    PageRequestJh1DTO requestDTO
  ) {
    PageResponseJh1DTO<ProductJh1DTO> pageResponseDTO = adminService.listProducts(requestDTO);
    return ResponseEntity.ok().body(pageResponseDTO);
  }

  @PostMapping("/product")
  public ResponseEntity<Map<String, Object>> registerProduct(@RequestBody ProductJh1DTO dto) {
    Long productNo = adminService.registerProduct(dto);
    return ResponseEntity.ok().body(Map.of("success", true, "productNo", productNo));
  }

  @PutMapping("/product")
  public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody ProductJh1DTO dto) {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(Map.of("success", false, "message", "Not implemented!"));
  }



  @GetMapping("/member/list")
  public ResponseEntity<PageResponseJh1DTO<MemberDTO>> listMembers(PageRequestJh1DTO requestDTO) {
    PageResponseJh1DTO<MemberDTO> membersDTO = adminService.listMembers(requestDTO);
    return ResponseEntity.ok().body(membersDTO);
  }

  @PostMapping("/member/givePoint")
  public ResponseEntity<Map<String, Object>> givePoint(@RequestBody MemberAddPointDTO dto) {
    adminService.givePointTo(dto.getMemberNo(), dto.getAmount());
    return ResponseEntity.ok().body(Map.of("success", true));
  }



  @GetMapping("/freeBoard/list")
  public ResponseEntity<PageResponseJh1DTO<FreeBoardJh1DTO>> listBoards(
    PageRequestJh1DTO requestDTO
  ) {
    PageResponseJh1DTO<FreeBoardJh1DTO> freeBoardList = adminService.getFreeBoardList(requestDTO);
    return ResponseEntity.ok().body(freeBoardList);
  }
}
