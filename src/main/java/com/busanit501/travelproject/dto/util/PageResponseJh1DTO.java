package com.busanit501.travelproject.dto.util;

import com.busanit501.travelproject.dto.util.reservationPageDTO.HcbPageRequestDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * PageRequestJh1DTO를 사용하는 ResponseDTO
 *
 * @author 원종호
 * @param <T>
 */
@Data
@ToString
public class PageResponseJh1DTO<T> {
    private static final int PAGINATION_WINDOW_SIZE = 10;

    private List<T> dtoList;
    private int page;
    private int size;
    private int start;
    private int end;
    private int last;
    private int total;
    private boolean next;
    private boolean prev;

    @Builder
    public PageResponseJh1DTO(List<T> dtoList, int total, PageRequestJh1DTO pageRequestDTO) {
        this.total = total;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.dtoList = dtoList;
        this.last = (int)Math.ceil((double) total /size);
        this.end = (int)Math.ceil((double) page / PAGINATION_WINDOW_SIZE)*PAGINATION_WINDOW_SIZE;
        this.end = Math.min(end, last);
        this.start = end - PAGINATION_WINDOW_SIZE + 1;
        this.start = Math.max(1, start);
        this.next = last > end;
        this.prev = start > 1;
    }
}
