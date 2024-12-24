package com.busanit501.travelproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductJh1DTO {
  private Long productNo;
  private String name;
  private String description;
  private Long price;
  private Long locationNo;
  private LocalDate startDate;
  private LocalDate endDate;
  private int capacity;
  private String imagePath;
  private List<Object> reservations;
  private List<Object> reviews;

  @JsonProperty("until")
  public Period getUntil() {
    LocalDate today = LocalDate.now();
    Period until = today.until(startDate);
    return until;
  }
}
