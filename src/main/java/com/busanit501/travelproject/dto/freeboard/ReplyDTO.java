package com.busanit501.travelproject.dto.freeboard;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {

    private Long replyNo;

    @NotNull
    private Long freeBoardNo;

    @NotEmpty
    private String replyText;

    @NotEmpty
    private String replier;

    private LocalDateTime regDate,modDate;


}
