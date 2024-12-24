package com.busanit501.travelproject.service.reservation;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public interface ReservationService {
    Long registerReservation(ReservationDTO reservationDTO);
    Long updateReservation(ReservationDTO reservationDTO);
    Long deleteReservation(Long reservationNo);
    HcbPageResponseDTO<ReservationDTO> getReservationUser(Long memberNo, ReservationOrder reservationOrder, HcbPageRequestDTO hcbPageRequestDTO);
    HcbPageResponseDTO<ReservationDTO> getReservationAdmin(Long productNo, HcbPageRequestDTO hcbPageRequestDTO);
    default ReservationDTO entityToDto (Reservation reservation) {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .reservationNo(reservation.getReservationNo())
                .memberNo(reservation.getMember().getMemberNo())
                .productNo(reservation.getProduct().getProductNo())
                .ReservationOrder(reservation.getReservationOrder())
                .build();
        return reservationDTO;
    }
}
