package com.busanit501.travelproject.controller.reservation;

import com.busanit501.travelproject.annotation.member.Member;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationUserDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.service.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationRestController {
    private final ReservationService reservationService;

    @GetMapping("/userReservation/{reservationOrder}")
    @Member
    public HcbPageResponseDTO<ReservationUserDTO> getUserReservation(
            @CookieValue(value = "memberNo", required = false) String memberNoCookie,
            HttpServletRequest request, RedirectAttributes redirectAttributes,
            @PathVariable ReservationOrder reservationOrder, HcbPageRequestDTO pageRequestDTO) throws BindException {
        Long memberNo = memberNoCookie != null ? Long.parseLong(memberNoCookie) : null;
        HcbPageResponseDTO<ReservationUserDTO> result = reservationService.getReservationUser(memberNo, reservationOrder, pageRequestDTO);
        return result;
    }
    //아직 어드민 어소리티 관련 확인 필요
    @GetMapping("/adminReservation/{productNo}")
    public HcbPageResponseDTO<ReservationDTO> getAdminReservation(
            @PathVariable Long productNo, HcbPageRequestDTO pageRequestDTO) {
        return reservationService.getReservationAdmin(productNo, pageRequestDTO);
    }

    @PostMapping("/reg")
    public Map<String,Long> reg(
            @Valid @RequestBody ReservationDTO reservationDTO, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long result = reservationService.registerReservation(reservationDTO);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/edit")
    public Map<String,Long> edit(
            @Valid @RequestBody ReservationDTO reservationDTO, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long result = reservationService.updateReservation(reservationDTO);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/delete/{reservationNo}")
    public Map<String,Long> delete(@PathVariable Long reservationNo) {
        Long result = reservationService.deleteReservation(reservationNo);
        return Map.of("reservationNo", result);
    }

    @DeleteMapping("/delete/now/{reservationNo}")
    public Map<String,Long> deleteNow(@PathVariable Long reservationNo) {
        Long result = reservationService.deleteReservationNow(reservationNo);
        return Map.of("reservationNo", result);
    }

    @PutMapping("/fee/{reservationNo}")
    public Map<String,Boolean> fee(@PathVariable Long reservationNo) {
        boolean result = reservationService.feePayment(reservationNo);
        return Map.of("paymentCompCheck", result);
    }

    @GetMapping("/best")
    public List<ProductJh1DTO> bestProduct() {
        return reservationService.getBestProducts();
    }
}
