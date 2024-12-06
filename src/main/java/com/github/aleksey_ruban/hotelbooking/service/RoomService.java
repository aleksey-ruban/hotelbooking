package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> findRoomsByGuestsCount(Integer guestsCount);
    Optional<Room> findById(Long id);
}
