package com.busanit501.travelproject.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 임의 페이지 링크를 만드는 기능이 추가된 PageRequestDTO
 * @author 원종호
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageRequestJh1DTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 20;

    public String getPageLink() {
        return "page=" + page + "&size=" + size;
    }

    public String getPageLinkOf(int page) {
        return "page=" + page + "&size=" + size;
    }
}
