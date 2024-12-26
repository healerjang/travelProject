package com.busanit501.travelproject.service.reservation;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public Long registerReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .reservationNo(reservationDTO.getReservationNo())
                .member(memberRepository.findByMemberNo(reservationDTO.getMemberNo()))
                // product 추가해야함
                .reservationOrder(reservationDTO.getReservationOrder())
                .build();
        Reservation result = reservationRepository.save(reservation);
        return result.getReservationNo();
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
