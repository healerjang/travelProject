package com.busanit501.travelproject.dto.reservation;

import com.busanit501.travelproject.domain.common.ReservationOrder;
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
