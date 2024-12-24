package com.busanit501.travelproject.service;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ReservationDTO;
import com.busanit501.travelproject.dto.util.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.HcbPageResponseDTO;

public interface ReservationService {
    Long registerReservation(ReservationDTO reservationDTO);
    Long updateReservation(ReservationDTO reservationDTO);
    Long deleteReservation(Long reservationNo);
    HcbPageResponseDTO<ReservationDTO> getReservationUser(Long reservationNo, ReservationOrder reservationOrder, HcbPageRequestDTO hcbPageRequestDTO);
    HcbPageResponseDTO<ReservationDTO> getReservationAdmin(Long productNo, HcbPageRequestDTO hcbPageRequestDTO);

}
