package com.busanit501.travelproject.service;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
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
}
