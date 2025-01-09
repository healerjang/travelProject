package com.busanit501.travelproject.controller.reservation;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.member.MemberDTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationUserDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.service.member.MemberService;
import com.busanit501.travelproject.service.reservation.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationRestController {
    private final ReservationService reservationService;
    private final MemberService memberService;

    @GetMapping("/userReservation/{reservationOrder}")
    @Member
    public HcbPageResponseDTO<ReservationUserDTO> getUserReservation(
            @PathVariable ReservationOrder reservationOrder, HcbPageRequestDTO pageRequestDTO, MemberDTO memberDTO) {
        return reservationService.getReservationUser(memberDTO.getMemberNo(), reservationOrder, pageRequestDTO);
    }

    //아직 어드민 어소리티 관련 확인 필요
    @GetMapping("/adminReservation/{productNo}")
    public HcbPageResponseDTO<ReservationDTO> getAdminReservation(
            @PathVariable Long productNo, HcbPageRequestDTO pageRequestDTO) {
        return reservationService.getReservationAdmin(productNo, pageRequestDTO);
    }

    @PostMapping("/reg")
    @Member
    public Map<String, Long> reg(
            @Valid @RequestBody ReservationDTO reservationDTO, BindingResult bindingResult,
            MemberDTO memberDTO) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        if (memberDTO == null) {
            throw new BindException(bindingResult);
        }
        reservationDTO.setMemberNo(memberDTO.getMemberNo());
        Long result = reservationService.registerReservation(reservationDTO);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/edit")
    public Map<String, Long> edit(
            @Valid @RequestBody ReservationDTO reservationDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long result = reservationService.updateReservation(reservationDTO);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/delete/{reservationNo}")
    public Map<String, Long> delete(@PathVariable Long reservationNo) {
        Long result = reservationService.deleteReservation(reservationNo);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/refund/{reservationNo}")
    public Map<String, Boolean> refund(@PathVariable Long reservationNo) {
        boolean result = reservationService.refundReservation(reservationNo);
        return Map.of("reservationNo", result);
    }

    @DeleteMapping("/delete/now/{reservationNo}")
    public Map<String, Long> deleteNow(@PathVariable Long reservationNo) {
        Long result = reservationService.deleteReservationNow(reservationNo);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/fee/{reservationNo}")
    public Map<String, Boolean> fee(@PathVariable Long reservationNo) {
        boolean result = reservationService.feePayment(reservationNo);
        return Map.of("paymentCompCheck", result);
    }

    @GetMapping("/memberPoint")
    @Member
    public Map<String, Integer> memberPoint(MemberDTO memberDTO) {
        MemberDTO result = memberService.getMember(memberDTO.getMemberNo());
        int memberPoint = result.getMemberPoint();
        return Map.of("memberPoint", memberPoint);
    }

    @GetMapping("/best")
    public List<ProductJh1DTO> bestProduct() {
        return reservationService.getBestProducts();
    }
}
