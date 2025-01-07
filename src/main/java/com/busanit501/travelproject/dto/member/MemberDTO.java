package com.busanit501.travelproject.dto.member;

import com.busanit501.travelproject.service.member.ResponseLogin;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO {
    private long memberNo;
    private String memberID;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private int memberPoint;
    private String memberUUID;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private ResponseLogin responseLogin;
}
