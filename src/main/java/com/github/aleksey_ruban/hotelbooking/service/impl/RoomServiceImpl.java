package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.entity.Room;
import com.github.aleksey_ruban.hotelbooking.repository.RoomRepository;
import com.github.aleksey_ruban.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> findRoomsByGuestsCount(Integer guestsCount) {
        return repository.findRoomsByGuestsCount(guestsCount);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return repository.findById(id);
    }
}
