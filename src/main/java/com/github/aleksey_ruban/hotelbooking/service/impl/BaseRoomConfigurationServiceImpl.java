package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.BaseRoomConfigurationRepository;
import com.github.aleksey_ruban.hotelbooking.service.BaseRoomConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseRoomConfigurationServiceImpl implements BaseRoomConfigurationService {

    private final BaseRoomConfigurationRepository repository;

    @Autowired
    public BaseRoomConfigurationServiceImpl(BaseRoomConfigurationRepository repository) {
        this.repository = repository;
    }


}
