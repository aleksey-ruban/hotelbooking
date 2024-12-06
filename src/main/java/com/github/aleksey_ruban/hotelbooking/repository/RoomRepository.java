package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r " +
            "WHERE r.extendedRoomConfiguration.baseRoomConfiguration.maxGuests >= :guestsCount")
    List<Room> findRoomsByGuestsCount(@Param("guestsCount") Integer guestsCount);
}
