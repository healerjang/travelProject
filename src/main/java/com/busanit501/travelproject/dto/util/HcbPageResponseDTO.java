package com.busanit501.travelproject.dto.util;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class HcbPageResponseDTO<T> {
    private List<T> dtoList;
    private int page;
    private int size;
    private int pageSize;
    private int start;
    private int end;
    private int last;
    private int total;
    private boolean next;
    private boolean prev;

    @Builder
    public HcbPageResponseDTO(List<T> dtoList, int total, HcbPageRequestDTO hcbPageRequestDTO) {
        this.total = total;
        this.page = hcbPageRequestDTO.getPage();
        this.size = hcbPageRequestDTO.getSize();
        this.pageSize = hcbPageRequestDTO.getPageSize();
        this.dtoList = dtoList;
        this.last = (int)Math.ceil((double) total /size);
        this.end = (int)Math.ceil((double) page /pageSize)*pageSize;
        this.end = Math.min(end, last);
        this.start = end - pageSize + 1;
        this.start = Math.max(1, start);
        this.next = last > end;
        this.prev = start > 1;
    }
}
