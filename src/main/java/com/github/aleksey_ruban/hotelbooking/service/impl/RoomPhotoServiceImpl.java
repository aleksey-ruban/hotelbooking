package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.repository.RoomPhotoRepository;
import com.github.aleksey_ruban.hotelbooking.service.RoomPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPhotoServiceImpl implements RoomPhotoService {

    private final RoomPhotoRepository repository;

    @Autowired
    public RoomPhotoServiceImpl(RoomPhotoRepository repository) {
        this.repository = repository;
    }


}
