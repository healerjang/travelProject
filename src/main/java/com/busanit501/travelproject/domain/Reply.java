package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @ToString.Exclude
    private Member member;
    @NotEmpty
    private String replyText;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private FreeBoard freeBoard;

    public void changeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }


    public void changeReplyText(String replyText) {
        this.replyText = replyText;
    }
}


