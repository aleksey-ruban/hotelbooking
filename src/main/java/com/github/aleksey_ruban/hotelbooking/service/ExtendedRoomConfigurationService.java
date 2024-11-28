package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;

import java.util.List;

public interface ExtendedRoomConfigurationService {
    ExtendedRoomConfiguration findById(Long id);
    List<ExtendedRoomConfiguration> findAllMainPage();
    List<ExtendedRoomConfiguration> finsAllOrderByCoast();
}
