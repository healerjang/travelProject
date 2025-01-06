package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ReservationByOrderRepository {
    @EntityGraph(attributePaths = {"product","product.location"})
    Page<ReservationUserDTO> selectReservationUser(Long memberNo, ReservationOrder reservationOrder, Pageable pageable);

    //    @Query("select r from Reservation r where r.product.productNo = :productNo")
    Page<ReservationDTO> selectReservationAdmin(Long productNo,Pageable pageable);

    List<Long> bestReservationProducts();
}
