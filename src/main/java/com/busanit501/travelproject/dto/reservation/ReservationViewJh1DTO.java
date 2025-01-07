package com.busanit501.travelproject.dto.reservation;

import com.busanit501.travelproject.domain.common.ReservationOrder;
import lombok.*;

import java.time.LocalDateTime;

/**
 * <p>
 *   Admin 페이지에서 회원별 예약 목록을 표시할 목적으로 만든 DTO
 * </p>
 * <p>
 *   INSERT, UPDATE 용으로 사용금지
 * </p>
 * @author 원종호
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationViewJh1DTO {
    private Long reservationNo;

    private Long memberNo;

    /** 표시용 회원 이름. null 가능*/
    private String memberName;

    private Long productNo;
    /** 표시용 상품명. null 가능 */
    private String productName;


    @Builder.Default
    private ReservationOrder ReservationOrder = com.busanit501.travelproject.domain.common.ReservationOrder.PENDING;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
