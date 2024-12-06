package com.github.aleksey_ruban.hotelbooking.repository;

import com.github.aleksey_ruban.hotelbooking.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {
    @Query("SELECT b FROM BookingRecord b " +
            "WHERE NOT (b.endDate <= :startDateTarget OR b.startDate >= :endDateTarget)")
    List<BookingRecord> findByDateRange(
            @Param("startDateTarget") LocalDate startDateTarget,
            @Param("endDateTarget") LocalDate endDateTarget
    );

    List<BookingRecord> findByClientId(Long id);
}
