package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.ExtendedRoomConfigurationRepository;
import com.github.aleksey_ruban.hotelbooking.service.ExtendedRoomConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtendedRoomConfigurationServiceImpl implements ExtendedRoomConfigurationService {

    private final ExtendedRoomConfigurationRepository repository;

    @Autowired
    public ExtendedRoomConfigurationServiceImpl(ExtendedRoomConfigurationRepository repository) {
        this.repository = repository;
    }
}
