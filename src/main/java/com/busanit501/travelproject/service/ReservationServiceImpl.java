package com.busanit501.travelproject.service;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ReservationDTO;
import com.busanit501.travelproject.dto.util.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.HcbPageResponseDTO;
import com.busanit501.travelproject.dto.util.PageRequestDTO;
import com.busanit501.travelproject.dto.util.PageResponseDTO;
import com.busanit501.travelproject.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long registerReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        return 0L;
    }

    @Override
    public Long updateReservation(ReservationDTO reservationDTO) {
        return 0L;
    }

    @Override
    public Long deleteReservation(Long reservationNo) {
        return 0L;
    }

    @Override
    public HcbPageResponseDTO<ReservationDTO> getReservationUser(Long reservationNo, ReservationOrder reservationOrder, HcbPageRequestDTO hcbPageRequestDTO) {
        return null;
    }

    @Override
    public HcbPageResponseDTO<ReservationDTO> getReservationAdmin(Long productNo, HcbPageRequestDTO hcbPageRequestDTO) {
        return null;
    }
}
