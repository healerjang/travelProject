package com.busanit501.travelproject.dto.productCgw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
