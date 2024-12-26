package com.busanit501.travelproject.service;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageResponseDTO;
import com.busanit501.travelproject.service.reservation.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

    @Test
    public void regTest() {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .productNo(2L)
                .memberNo(1L)
                .ReservationOrder(ReservationOrder.PENDING)
                .build();
        Long result = reservationService.registerReservation(reservationDTO);
        log.info(result);
    }

    @Test
    public void updTest() {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .reservationNo(54L)
                .productNo(2L)
                .memberNo(1L)
                .ReservationOrder(ReservationOrder.COMPLETED)
                .build();
        Long result = reservationService.updateReservation(reservationDTO);
    }
    @Test
    public void delTest() {
        reservationService.deleteReservation(54L);
    }
    @Test
    public void listTest() {
        HcbPageRequestDTO pRDTO = HcbPageRequestDTO.builder()
                .build();
        HcbPageResponseDTO<ReservationDTO> result = reservationService.getReservationUser(1L,ReservationOrder.CANCELLED,pRDTO);
        log.info(result);
    }
    @Test
    public void list2Test() {
        HcbPageRequestDTO pageRequestDTO = HcbPageRequestDTO.builder().build();
        HcbPageResponseDTO<ReservationDTO> result = reservationService.getReservationAdmin(2L,pageRequestDTO);
        log.info(result);
    }
}
