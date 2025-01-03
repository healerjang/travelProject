package com.busanit501.travelproject.service;

import com.busanit501.travelproject.service.reservation.ReservationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

//    @Test
//    public void regTest() {
//        ReservationDTO reservationDTO = ReservationDTO.builder()
//                .productNo(2L)
//                .memberNo(1L)
//                .ReservationOrder(ReservationOrder.PENDING)
//                .build();
//        Long result = reservationService.registerReservation(reservationDTO);
//        log.info(result);
//    }
//
//    @Test
//    public void updTest() {
//        ReservationDTO reservationDTO = ReservationDTO.builder()
//                .reservationNo(53L)
//                .productNo(2L)
//                .memberNo(1L)
//                .ReservationOrder(ReservationOrder.CANCELLED)
//                .build();
//        Long result = reservationService.updateReservation(reservationDTO);
//    }
//    @Test
//    public void delTest() {
//        reservationService.deleteReservation(54L);
//    }
//    @Test
//    public void listTest() {
//        HcbPageRequestDTO pRDTO = HcbPageRequestDTO.builder()
//                .build();
//        HcbPageResponseDTO<ReservationDTO> result = reservationService.getReservationUser(1L,ReservationOrder.CANCELLED,pRDTO);
//        log.info(result);
//    }
//    @Test
//    public void list2Test() {
//        HcbPageRequestDTO pageRequestDTO = HcbPageRequestDTO.builder().build();
//        HcbPageResponseDTO<ReservationDTO> result = reservationService.getReservationAdmin(2L,pageRequestDTO);
//        log.info(result);
//    }
}
