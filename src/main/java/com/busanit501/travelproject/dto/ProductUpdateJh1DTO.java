package com.busanit501.travelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 여행상품 업데이트 DTO
 * 여행상품 정보는 일단 민감하지 않은 정보만 업데이트 가능한 것으로
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateJh1DTO {
  private Long productNo;
  private String name;
  private String description;

  // 이미 예약한 회원이 있을 때 상품 가격이 바뀐다면 어떻게 할 것인가?
  //  private Long price;

  // 여행상품의 여행지가 갑자기 바뀌는 것이 가능한가?
  // private Long locationNo;

  // 업데이트 시에는 실제 여행지 정보는 필요하지 않다.
  //  private LocationValueJh1DTO location;

  // 갑자기 여행 일정이 바뀐다면?
  //  private LocalDate startDate;
  //  private LocalDate endDate;

  /**
   * 여행상품 최대 좌석 수 변경에 대해서
   * 최대 좌석 수가 순수히 증가하는 경우 OK
   * 새로운 최대 좌석 수가 원래 최대 좌석 수보다 적고, 현재 예약 중인 고객 수보다 많으면 일단 OK
   *  */
  private int capacity;
  private String imagePath;



  // 업데이트 시에 주입할 수 없는 정보
  //  private List<ReservationViewJh1DTO> reservations;
  //  private List<ReviewJh1DTO> reviews;
  //  @JsonProperty("until")
  //  public long getUntil() {
  //    LocalDate today = LocalDate.now();
  //    return today.until(startDate, ChronoUnit.DAYS);
  //  }
}
