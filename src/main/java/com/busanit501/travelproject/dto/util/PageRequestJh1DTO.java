package com.busanit501.travelproject.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Builder.Default
    private String[] searchFor = null;

    @Builder.Default
    private String search = null;

    public Pageable getPageable(String ...props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props));
    }

    public boolean isNotSearching() {
        return searchFor == null || searchFor.length == 0;
    }

    public boolean isSearchingFor(String s) {
        if (searchFor == null) return false;
        for (String sf : searchFor) {
            if (sf.equals(s)) return true;
        }
        return false;
    }

}
