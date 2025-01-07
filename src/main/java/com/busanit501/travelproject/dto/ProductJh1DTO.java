package com.busanit501.travelproject.dto;

import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationViewJh1DTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 여행상품 엔티티에서 예약 및 리뷰가 빠진 DTO
 */
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

  /**
   * <p>실제 여행지 정보</p>
   * <p>상황에 따라 null이 될수도 있다.</p>
   * */
  private LocationValueJh1DTO location;

  private LocalDate startDate;
  private LocalDate endDate;
  private int capacity;
  private String imagePath;
  private List<ReservationViewJh1DTO> reservations;
  private List<ReviewJh1DTO> reviews;

  @JsonProperty("until")
  public long getUntil() {
    LocalDate today = LocalDate.now();
//    Period until = today.until(startDate);
    return today.until(startDate, ChronoUnit.DAYS);
  }
}
