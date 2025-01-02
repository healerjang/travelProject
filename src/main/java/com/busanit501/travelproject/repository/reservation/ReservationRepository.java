package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationByOrderRepository {
    // insert delete 있음
    // select 인데 유저용 하나, 관리자용 하나
    // 유저용에는 order 따라 필터
}
