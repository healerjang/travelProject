package com.busanit501.travelproject.service.reservation;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.member.MemberFullDTO;
import com.busanit501.travelproject.dto.member.UpdateDTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import com.busanit501.travelproject.service.admin.AdminJh1Service;
import com.busanit501.travelproject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final ProductJh1Repository productJh1Repository;
    private final AdminJh1Service adminJh1Service;

    @Override
    public Long registerReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .member(memberRepository.findByMemberNo(reservationDTO.getMemberNo()))
                .product(productJh1Repository.findProductByProductNo(reservationDTO.getProductNo()).orElseThrow())
                .reservationOrder(reservationDTO.getReservationOrder())
                .build();
        Reservation result = reservationRepository.save(reservation);
        return result.getReservationNo();
    }

    @Override
    public Long updateReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .reservationNo(reservationDTO.getReservationNo())
                .member(memberRepository.findByMemberNo(reservationDTO.getMemberNo()))
                .product(productJh1Repository.findProductByProductNo(reservationDTO.getProductNo()).orElseThrow())
                .reservationOrder(reservationDTO.getReservationOrder())
                .build();
        Reservation result = reservationRepository.save(reservation);
        return result.getReservationNo();
    }

    @Override
    public Long deleteReservation(Long reservationNo) {
        Reservation reservation = reservationRepository.findById(reservationNo).orElseThrow();
        reservation.changeOrder(ReservationOrder.CANCELLED);
        reservationRepository.save(reservation);
        return reservation.getReservationNo();
    }

    @Override
    public Long deleteReservationNow(Long reservationNo) {
        reservationRepository.deleteById(reservationNo);
        return reservationNo;
    }

    @Override
    public HcbPageResponseDTO<ReservationDTO> getReservationUser(
            Long memberNo, ReservationOrder reservationOrder, HcbPageRequestDTO hcbPageRequestDTO) {
        Page<ReservationDTO> result = reservationRepository.selectReservationUser(memberNo, reservationOrder, hcbPageRequestDTO.getPageable("reservationNo"));
        return HcbPageResponseDTO.<ReservationDTO>builder()
                .dtoList(result.getContent())
                .hcbPageRequestDTO(hcbPageRequestDTO)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public HcbPageResponseDTO<ReservationDTO> getReservationAdmin(
            Long productNo, HcbPageRequestDTO hcbPageRequestDTO) {
        Page<ReservationDTO> result = reservationRepository.selectReservationAdmin(productNo, hcbPageRequestDTO.getPageable("reservationNo"));
        return HcbPageResponseDTO.<ReservationDTO>builder()
                .dtoList(result.getContent())
                .hcbPageRequestDTO(hcbPageRequestDTO)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public List<ProductJh1DTO> getBestProducts() {
        List<Long> result = reservationRepository.bestReservationProducts();
        List<ProductJh1DTO> dtoList = result.stream().map(productNo -> {
            Product ett = productJh1Repository.findProductByProductNo(productNo).orElseThrow();
            ProductJh1DTO dto = adminJh1Service.productEntityToDTO(ett);
            return dto;
        }).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @Transactional
    public boolean feePayment(Long reservationNo) {
        Reservation reservation = reservationRepository.findById(reservationNo).orElseThrow();
        Long memberNo = reservation.getMember().getMemberNo();
        Long productNo = reservation.getProduct().getProductNo();
        Member member = memberRepository.findById(memberNo).orElseThrow();
        int memberPoint = member.getMemberPoint();
        int productPrice = productJh1Repository.findProductByProductNo(productNo).orElseThrow().getPrice().intValue();
        if (memberPoint < productPrice) return false;
        member.updateMemberData(UpdateDTO.builder()
                .memberNo(member.getMemberNo())
                .memberID(member.getMemberID())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPhone(member.getMemberPhone())
                .memberPoints(memberPoint - productPrice)
                .build());
        memberRepository.save(member);
        reservation.changeOrder(ReservationOrder.COMPLETED);
        reservationRepository.save(reservation);
        return true;
    }
}
