package com.busanit501.travelproject.service.productCgw;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.repository.ProductCgw.ProductCgwRepository;
import com.busanit501.travelproject.repository.member.MemberRepository;
import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReservationCgwDummyTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductCgwRepository productCgwRepository;

    @Test
    public void reservationDummyTest() {
        IntStream.range(1, 11).forEach(i -> {
            reservationRepository.save(Reservation.builder()
                            .member(memberRepository.findById(1L).orElse(null))
                            .product(productCgwRepository.findById((long)i).orElse(null))
                            .reservationOrder(ReservationOrder.COMPLETED)
                    .build());
        });
    }

}
