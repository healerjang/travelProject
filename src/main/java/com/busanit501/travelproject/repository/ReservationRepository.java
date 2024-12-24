package com.busanit501.travelproject.repository;

import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationByOrderRepository {
    // insert delete 있음
    // select 인데 유저용 하나, 관리자용 하나
    // 유저용에는 order 따라 필터
}
