package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void insertReservation() {
        for (Long i = 1L; i < 4L; i++) {
            for (int j = 1; j < 11; j++) {
                Reservation result = reservationRepository.save(Reservation.builder()
                        .member(Member.builder().memberNo(1L).build())
                        .product(Product.builder().productNo(i).build())
                        .reservationOrder(ReservationOrder.PENDING)
                        .build());
            }
        }
    }
}
