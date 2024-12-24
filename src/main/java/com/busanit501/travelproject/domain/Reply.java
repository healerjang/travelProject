package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;
    @Column(nullable = false, length = 1000)
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
}
