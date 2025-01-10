package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationByOrderRepository {
    // insert delete 있음
    // select 인데 유저용 하나, 관리자용 하나
    // 유저용에는 order 따라 필터
    // 지연 삭제 필요
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservation WHERE reservation.reservation_status = 'CANCELLED' AND reservation.mod_date <= :howManyTimePassed", nativeQuery = true)
    void delayedDelete(LocalDateTime howManyTimePassed);

    @Query(value = "SELECT * FROM reservation WHERE reservation.member_no = :memberNo and reservation.product_no = :productNo", nativeQuery = true)
    Optional<Reservation> checkReservation(Long memberNo, Long productNo);
}
