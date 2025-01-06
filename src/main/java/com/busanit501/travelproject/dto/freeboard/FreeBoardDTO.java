package com.busanit501.travelproject.dto.freeboard;

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
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
