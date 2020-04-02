package com.player.service;

import com.player.model.entity.MusicEntity;

import java.util.List;

public interface MusicService {
    List<MusicEntity> findAll();
    List<MusicEntity> findByName(String name);
    MusicEntity findById(int id);
    List<MusicEntity> findBySinger(int singerId);
    void save(MusicEntity music);
    void update(MusicEntity music);
    void delete(MusicEntity music);
}
