package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.entity.BookingRecord;
import com.github.aleksey_ruban.hotelbooking.entity.Room;
import com.github.aleksey_ruban.hotelbooking.repository.BookingRecordRepository;
import com.github.aleksey_ruban.hotelbooking.service.BookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingRecordServiceImpl implements BookingRecordService {

    private final BookingRecordRepository repository;

    @Autowired
    public BookingRecordServiceImpl(BookingRecordRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<BookingRecord> findByDateRange(LocalDate startTarget, LocalDate endTarget) {
        return repository.findByDateRange(startTarget, endTarget);
    }

    @Override
    public BookingRecord save(BookingRecord bookingRecord) {
        return repository.save(bookingRecord);
    }

    @Override
    public List<BookingRecord> findByClientId(Long id) {
        return repository.findByClientId(id);
    }

    @Override
    public Optional<BookingRecord> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
