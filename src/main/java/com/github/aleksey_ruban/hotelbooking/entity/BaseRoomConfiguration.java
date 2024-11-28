package com.github.aleksey_ruban.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BaseRoomConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String cardDescription;

    @Column(nullable = false)
    private String fullDescription;

    @Column(nullable = false)
    private Integer baseCoast;

    @Column(nullable = false)
    private Integer area;

    @Column(nullable = false)
    private Integer maxGuests;

    @Column(nullable = false)
    private Integer rooms;

    @OneToMany(mappedBy = "baseRoomConfiguration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtendedRoomConfiguration> extendedRoomConfigurations;

    @OneToMany(mappedBy = "baseRoomConfiguration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriceSchedule> priceSchedules;
}
