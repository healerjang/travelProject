package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    public void changeTitleContent(@NotEmpty @Size(min = 3, max = 100) String title, @NotEmpty String content) {
        this.title = title;
        this.content = content;
    }
}
