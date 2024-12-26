package com.busanit501.travelproject.domain;

import com.busanit501.travelproject.dto.member.UpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberNo;
    @Column(nullable = false, unique = true, length = 20)
    private String memberID;
    @Column(nullable = false)
    private String memberPassword;
    @Column(nullable = false, unique = true, length = 20)
    private String memberName;
    @Column(nullable = false, unique = true)
    private String memberEmail;
    @Column(nullable = false, unique = true)
    private String memberPhone;
    private int memberPoint;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FreeBoard> freeBoards;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reply> replies;

    public void updateMemberData(UpdateDTO updateDTO) {
        this.memberID = updateDTO.getMemberID();
        this.memberPassword = updateDTO.getMemberPassword();
        this.memberName = updateDTO.getMemberName();
        this.memberEmail = updateDTO.getMemberEmail();
        this.memberPhone = updateDTO.getMemberPhone();
    }
}
