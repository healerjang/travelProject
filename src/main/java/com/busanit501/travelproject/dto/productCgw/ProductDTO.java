package com.busanit501.travelproject.dto.productCgw;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
    private long productNo;
    private String name;
    private String description;
    private Long price;
    private Long locationNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private String imagePath;
}
