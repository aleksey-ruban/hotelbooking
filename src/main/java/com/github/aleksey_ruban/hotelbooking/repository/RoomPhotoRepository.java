package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomPhotoRepository extends JpaRepository<RoomPhoto, Long> {
    @Query("SELECT p FROM RoomPhoto p WHERE p.extendedRoomConfiguration.id=:extConfigId ORDER BY p.outputOrder ASC")
    List<RoomPhoto> findByConfigByOrder(@Param("extConfigId") Long extConfigId);
}
