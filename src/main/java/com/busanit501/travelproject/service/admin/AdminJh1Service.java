package com.busanit501.travelproject.service.admin;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.dto.*;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.PageRequestJh1DTO;
import com.busanit501.travelproject.dto.util.PageResponseJh1DTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AdminJh1Service {

  default ProductJh1DTO productEntityToDTO(Product product) {
    return ProductJh1DTO.builder()
      .productNo(product.getProductNo())
      .name(product.getName())
      .description(product.getDescription())
      .price(product.getPrice())
      .locationNo(product.getLocation().getLocationNo())
      .startDate(product.getStartDate())
      .endDate(product.getEndDate())
      .capacity(product.getCapacity())
      .imagePath(product.getImagePath())
      .build();
  }

  default MemberDTO memberEntityToDTO(Member member) {
    return MemberDTO.builder()
      .memberNo(member.getMemberNo())
      .memberID(member.getMemberID())
      .memberName(member.getMemberName())
      .memberEmail(member.getMemberEmail())
      .memberPhone(member.getMemberPhone())
      .memberPoint(member.getMemberPoint())
      .memberUUID(member.getMemberUUID())
      .regDate(member.getRegDate())
      .modDate(member.getModDate())
      .build();
  }

  default MemberFullDTO memberEntityToFullDTO(Member member) {
    List<ReservationDTO> reservationList = member.getReservations().stream().map(
      r -> ReservationDTO.builder()
        .reservationNo(r.getReservationNo())
        .memberNo(member.getMemberNo())
        .productNo(r.getProduct().getProductNo())
        .build()
    ).toList();

    List<ReviewJh1DTO> reviewList = member.getReviews().stream().map(
      r -> ReviewJh1DTO.builder()
        .reviewNo(r.getReviewNo())
        .reviewContent(r.getReviewContent())
        .rating(r.getRating())
        .productNo(r.getProduct().getProductNo())
        .memberNo(member.getMemberNo())
        .build()
    ).toList();

    List<FreeBoardJh1DTO> freeBoardList = member.getFreeBoards().stream().map(
        fb -> FreeBoardJh1DTO.builder()
          .freeBoardNo(fb.getFreeBoardNo())
          .title(fb.getTitle())
          .content(fb.getContent())
          .memberNo(member.getMemberNo())
          .build()
      ).toList();

    List<ReplyJh1DTO> replyList = member.getReplies().stream().map(
      re -> ReplyJh1DTO.builder()
        .replyNo(re.getReplyNo())
        .content(re.getContent())
        .memberNo(member.getMemberNo())
        .build()
    ).toList();

    return MemberFullDTO.builder()
      .memberNo(member.getMemberNo())
      .memberID(member.getMemberID())
      .memberName(member.getMemberName())
      .memberEmail(member.getMemberEmail())
      .memberPhone(member.getMemberPhone())
      .memberPoint(member.getMemberPoint())
      .memberUUID(member.getMemberUUID())
      .regDate(member.getRegDate())
      .modDate(member.getModDate())
      .reservations(reservationList)
      .reviews(reviewList)
      .freeBoards(freeBoardList)
      .replies(replyList)
      .build();
  }

  @Transactional
  List<LocationValueJh1DTO> getLocationsOnly();

  Long registerLocation(LocationValueJh1DTO dto);

  @Transactional
  Long registerProduct(ProductJh1DTO dto);

  ProductJh1DTO getProductTmp(Long id);

  PageResponseJh1DTO<ProductJh1DTO> listProducts(PageRequestJh1DTO requestDTO);

  PageResponseJh1DTO<MemberDTO> listMembers(PageRequestJh1DTO requestDTO);

  MemberFullDTO getMemberFullSupport(long memberNo);

  void givePointTo(long memberNo, int amount);

  PageResponseJh1DTO<FreeBoardJh1DTO> getFreeBoardList(PageRequestJh1DTO requestDTO);
}
