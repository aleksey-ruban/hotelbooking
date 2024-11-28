package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
