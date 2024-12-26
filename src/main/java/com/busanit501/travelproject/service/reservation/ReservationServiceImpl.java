package com.busanit501.travelproject.service.reservation;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.repository.ProductJh1Repository;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final ProductJh1Repository productJh1Repository;

    @Override
    public Long registerReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Reservation.builder()
                .member(memberRepository.findByMemberNo(reservationDTO.getMemberNo()))
                .product(productJh1Repository.findByProductNo(reservationDTO.getProductNo()))
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
                .product(productJh1Repository.findByProductNo(reservationDTO.getProductNo()))
                .reservationOrder(reservationDTO.getReservationOrder())
                .build();
        Reservation result = reservationRepository.save(reservation);
        return result.getReservationNo();
    }

    @Override
    public Long deleteReservation(Long reservationNo) {
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
}
