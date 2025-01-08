package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "location")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long price;
    @ManyToOne
    @JoinColumn(name = "location_no", nullable = false)
    private Location location;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private int capacity;
    private String imagePath;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    /**
     * 비즈니스 모델에 민감하지 않은 정보를 업데이트한다.
     * @author 원종호
     */
    public void updateProductSafe(String name, String description, int capacity, String imagePath) {
        this.name = name;
        this.description = description;
        if (capacity > this.capacity) this.capacity = capacity;
        this.imagePath = imagePath;
    }
}
