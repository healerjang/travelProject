package com.busanit501.travelproject.dto.reservation;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RefundDTO {
    private Long reservationNo;
    private int refundPercent;
}
