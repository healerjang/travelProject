package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;
    @Column(nullable = false)
    private String reviewContent;
    @Column(nullable = false)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
}
