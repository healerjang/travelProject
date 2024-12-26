package com.busanit501.travelproject.dto.util.reservationPageDTO;

import com.busanit501.travelproject.dto.util.PageRequestDTO;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HcbPageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    @Builder.Default
    private int pageSize = 10;
    public Pageable getPageable(String ...props) {
        Pageable pageable = PageRequest.of(this.getPage() - 1, this.getPageSize(), Sort.by(props).descending());
        return pageable;

    }
    public String getPageLink() {
        return "page=" + page + "&size=" + size;
    }
}
