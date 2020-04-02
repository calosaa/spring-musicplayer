package com.player.model.dao;

import com.player.model.entity.MusicEntity;

import java.util.List;

public interface MusicDao {
    List findAll();
    List findByName(String name);
    MusicEntity findById(int id);
    List findBySinger(int singerId);
    void save(MusicEntity music);
    void update(MusicEntity music);
    void delete(MusicEntity music);
}
