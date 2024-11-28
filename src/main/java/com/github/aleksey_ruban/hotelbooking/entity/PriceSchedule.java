package com.github.aleksey_ruban.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PriceSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_room_configuration_id", referencedColumnName = "id", nullable = false)
    private BaseRoomConfiguration baseRoomConfiguration;

    @Column(nullable = false)
    private Integer coast;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;
}
