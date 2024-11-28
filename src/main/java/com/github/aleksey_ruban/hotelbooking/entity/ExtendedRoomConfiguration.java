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
public class ExtendedRoomConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_room_configuration_id", referencedColumnName = "id", nullable = false)
    private BaseRoomConfiguration baseRoomConfiguration;

    @Column(unique = true, nullable = false)
    private String bookingName;

    @OneToMany(mappedBy = "extendedRoomConfiguration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomPhoto> roomPhotos;

    @Column(nullable = false)
    private Boolean isOnMain;

    @OneToMany(mappedBy = "extendedRoomConfiguration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms;
}