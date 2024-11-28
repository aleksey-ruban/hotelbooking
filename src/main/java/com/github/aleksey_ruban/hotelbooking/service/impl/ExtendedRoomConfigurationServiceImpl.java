package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.entity.ExtendedRoomConfiguration;
import com.github.aleksey_ruban.hotelbooking.repository.ExtendedRoomConfigurationRepository;
import com.github.aleksey_ruban.hotelbooking.service.ExtendedRoomConfigurationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtendedRoomConfigurationServiceImpl implements ExtendedRoomConfigurationService {

    private final ExtendedRoomConfigurationRepository repository;

    @Autowired
    public ExtendedRoomConfigurationServiceImpl(ExtendedRoomConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExtendedRoomConfiguration findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExtendedRoomConfiguration with Id " + id + " not found"));
    }

    @Override
    public List<ExtendedRoomConfiguration> findAllMainPage() {
        return repository.findAllMainPage();
    }

    @Override
    public List<ExtendedRoomConfiguration> finsAllOrderByCoast() {
        return repository.findAllOrderByBaseCoast();
    }
}
