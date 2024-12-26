package com.busanit501.travelproject.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
