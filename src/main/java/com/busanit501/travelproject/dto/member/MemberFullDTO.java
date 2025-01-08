package com.busanit501.travelproject.dto.member;

import com.busanit501.travelproject.dto.FreeBoardJh1DTO;
import com.busanit501.travelproject.dto.ReplyJh1DTO;
import com.busanit501.travelproject.dto.ReviewJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationViewJh1DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 단일 사용자에 대한 "모든" 정보가 담겨 있는 클래스. 예약, 리뷰, 자유게시판, 댓글까지 모두 포함
 * </p>
 * <p>
 * 원래 Admin 페이지에서 사용하려고 만들었음
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFullDTO {
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private long memberNo;
    private String memberID;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private int memberPoint;
    private List<ReservationViewJh1DTO> reservations;
    private List<ReviewJh1DTO> reviews;
    private List<FreeBoardJh1DTO> freeBoards;
    private List<ReplyJh1DTO> replies;
}
