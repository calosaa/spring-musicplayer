package com.player.service;

import com.player.model.entity.SingerEntity;

import java.util.List;

public interface SingerService {
    List<SingerEntity> findAll();
    List<SingerEntity> findByName(String name);
    SingerEntity findById(int id);
    void save(SingerEntity singer);
    void update(SingerEntity singer);
    void delete(SingerEntity singer);
}
