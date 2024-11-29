package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtendedRoomConfigurationRepository extends JpaRepository<ExtendedRoomConfiguration, Long> {
    @Query("SELECT c FROM ExtendedRoomConfiguration c WHERE c.isOnMain=true ORDER BY c.baseRoomConfiguration.baseCoast ASC")
    List<ExtendedRoomConfiguration> findAllMainPage();

    @Query("SELECT c FROM ExtendedRoomConfiguration c ORDER BY c.baseRoomConfiguration.baseCoast ASC")
    List<ExtendedRoomConfiguration> findAllOrderByBaseCoast();
}
