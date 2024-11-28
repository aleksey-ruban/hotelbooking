package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.PriceScheduleRepository;
import com.github.aleksey_ruban.hotelbooking.service.PriceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceScheduleServiceImpl implements PriceScheduleService {

    private final PriceScheduleRepository repository;

    @Autowired
    public PriceScheduleServiceImpl(PriceScheduleRepository repository) {
        this.repository = repository;
    }


}
