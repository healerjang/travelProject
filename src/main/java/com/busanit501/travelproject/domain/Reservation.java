package com.busanit501.travelproject.domain;

import com.busanit501.travelproject.domain.common.ReservationOrder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNo;
    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;
    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status", nullable = false)
    private ReservationOrder reservationOrder;

    public void changeOrder(ReservationOrder reservationOrder) {
        this.reservationOrder = reservationOrder;
    }
}
