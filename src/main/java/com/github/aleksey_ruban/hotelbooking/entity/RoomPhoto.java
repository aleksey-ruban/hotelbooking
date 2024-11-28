package com.github.aleksey_ruban.hotelbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoomPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String photoPath;

    @Column(nullable = false)
    private Integer outputOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "extended_room_configuration_id", referencedColumnName = "id", nullable = false)
    private ExtendedRoomConfiguration extendedRoomConfiguration;
}
