package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.BookingRecord;
import com.github.aleksey_ruban.hotelbooking.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRecordService {
    List<BookingRecord> findByDateRange(LocalDate startTarget, LocalDate endTarget);
    BookingRecord save(BookingRecord bookingRecord);
    List<BookingRecord> findByClientId(Long id);
    Optional<BookingRecord> findById(Long id);
    void delete(Long id);
}
