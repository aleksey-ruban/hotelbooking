package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtendedRoomConfigurationRepository extends JpaRepository<ExtendedRoomConfiguration, Long> {
}
