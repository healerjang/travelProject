package com.busanit501.travelproject.service.admin;

import com.busanit501.travelproject.dto.*;
import com.busanit501.travelproject.dto.freeboard.FreeBoardJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.dto.util.PageResponseJh1DTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AdminJh1Service {

  @Transactional
  List<LocationValueJh1DTO> getLocationsOnly();

  Long registerLocation(LocationValueJh1DTO dto);

  @Transactional
  Long registerProduct(ProductJh1DTO dto);

  void updateProduct(ProductUpdateJh1DTO dto);

  /** getProductCompact를 쓰시오 */
  @Deprecated
  ProductJh1DTO getProductTmp(Long id);

  ProductJh1DTO getProductCompact(Long id);

  ProductJh1DTO getProductFull(Long id);

  PageResponseJh1DTO<ProductJh1DTO> listProducts(PageRequestJh1DTO requestDTO);

  PageResponseJh1DTO<MemberDTO> listMembers(PageRequestJh1DTO requestDTO);

  MemberFullDTO getMemberFullSupport(long memberNo);

  void givePointTo(long memberNo, int amount);

  PageResponseJh1DTO<FreeBoardJh1DTO> getFreeBoardList(PageRequestJh1DTO requestDTO);

  List<ProductImageAdminDTO> getProductImages();
}
