package com.busanit501.travelproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationNo;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Set<Product> products;
}
