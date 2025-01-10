package com.busanit501.travelproject.service.reservation;

import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationUserDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;

import java.util.List;

public interface ReservationService {
    Boolean registerReservation(ReservationDTO reservationDTO);
    Long updateReservation(ReservationDTO reservationDTO);
    Long deleteReservation(Long reservationNo);
    boolean refundReservation(Long reservationNo, int refundPercent);
    Long deleteReservationNow(Long reservationNo);
    HcbPageResponseDTO<ReservationUserDTO> getReservationUser(Long memberNo, ReservationOrder reservationOrder, HcbPageRequestDTO hcbPageRequestDTO);
    HcbPageResponseDTO<ReservationDTO> getReservationAdmin(Long productNo, HcbPageRequestDTO hcbPageRequestDTO);
    List<ProductJh1DTO> getBestProducts();
    boolean feePayment(Long reservationNo);
    Boolean checkReservation(Long memberNo, Long productNo);
}
