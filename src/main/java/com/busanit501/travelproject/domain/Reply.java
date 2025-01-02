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
    private Member member;
    @NotEmpty
    private String replyText;
    @ManyToOne(fetch = FetchType.LAZY) // fetch = 가져오는거 , LAZY = 사용하는 시점에 조회함(늦게 가져오는거)
    //FetchType.EAGER, 즉시로딩, 사용 안해도 조회 하겠다.
    private FreeBoard freeBoard; // 부모의 게시글 번호,

    public void changeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }

    // 수정 시, 내용만 변경하기위해서 사용함, 세터 안쓰는 이유 => 불변성을 유지하기 위해서
    public void changeReplyText(String replyText) {
        this.replyText = replyText;
    }
}


