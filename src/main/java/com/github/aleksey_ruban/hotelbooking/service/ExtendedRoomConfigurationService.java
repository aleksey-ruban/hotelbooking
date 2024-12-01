package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;

import java.util.List;
import java.util.Optional;

public interface ExtendedRoomConfigurationService {
    Optional<ExtendedRoomConfiguration> findById(Long id);
    List<ExtendedRoomConfiguration> findAllMainPage();
    List<ExtendedRoomConfiguration> finsAllOrderByCoast();
}
