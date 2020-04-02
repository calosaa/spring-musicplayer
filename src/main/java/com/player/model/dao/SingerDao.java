package com.player.model.dao;

import com.player.model.entity.SingerEntity;

import java.util.List;

public interface SingerDao {
    List findAll();
    List findByName(String name);
    SingerEntity findById(int id);
    void save(SingerEntity singer);
    void update(SingerEntity singer);
    void delete(SingerEntity singer);
}
