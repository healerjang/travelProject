package com.busanit501.travelproject.dto.freeboard;

import com.busanit501.travelproject.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardDTO {
    private  Long freeBoardNo;
    @NotEmpty
    @Size(min = 3, max = 100)
    private  String title;

    @NotEmpty
    private  String content;

//    @NotNull
    private long memberNo;
    private String memberName; // 작성자 이름을 저장할 수 있도록 추가

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
