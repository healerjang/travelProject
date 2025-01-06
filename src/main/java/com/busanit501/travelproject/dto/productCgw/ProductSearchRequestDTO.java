package com.busanit501.travelproject.dto.productCgw;

import com.busanit501.travelproject.dto.util.PageRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductSearchRequestDTO extends PageRequestDTO {
    private Long locationNo;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getKeywordLink() {
        return "locationNo=" + locationNo + "&startDate=" + startDate + "&endDate=" + endDate;
    }

    public boolean isData() {
        return locationNo != null || startDate != null || endDate != null;
    }
}
