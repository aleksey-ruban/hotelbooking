package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.BookingRecordRepository;
import com.github.aleksey_ruban.hotelbooking.service.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingRecordServiceImpl implements BookingRecordService {

    private final BookingRecordRepository repository;

    @Autowired
    public BookingRecordServiceImpl(BookingRecordRepository repository) {
        this.repository = repository;
    }


}
