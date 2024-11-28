package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.RoomPhoto;

import java.util.List;

public interface RoomPhotoService {
    List<RoomPhoto> findRoomPhotoInOrder(Long extConfigId);
}
