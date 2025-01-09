package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardNo;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // cascade - sql 부모요소에게 수정,삭제 등 영향을 주면 자식 요소도 전부 받게 만들게 해야
    // 외래키 설정이 되어있어도 삭제가 된다.
    // 댓글 입장에서는 many to one 이지만
    // 우린 지금 게시판 로직을 만들고 있으니 게시판 입장에서 one to many  가 됨
    private Set<Reply> replies;

    public void changeTitleContent(@NotEmpty @Size(min = 3, max = 100) String title, @NotEmpty String content) {
        this.title = title;
        this.content = content;
    }
}
