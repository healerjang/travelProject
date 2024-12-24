package com.busanit501.travelproject.dto;

import com.busanit501.travelproject.domain.Member;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDTO { // validation 필요
    private Long reservationNo;
    private Long memberNo;
    private Long productNo;
    private ReservationOrder ReservationOrder;
}
