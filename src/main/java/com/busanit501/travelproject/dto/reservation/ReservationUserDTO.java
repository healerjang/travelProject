package com.busanit501.travelproject.dto.reservation;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationUserDTO {
    private Long reservationNo;
    private Long memberNo;
    private String productName;
    private String productDescription;
    private Long productPrice;
    private LocalDate productStartDate;
    private LocalDate productEndDate;
    private String productLocationCountry;
    private String productLocationCity;
    @Builder.Default
    private com.busanit501.travelproject.domain.common.ReservationOrder ReservationOrder = com.busanit501.travelproject.domain.common.ReservationOrder.PENDING;
}
