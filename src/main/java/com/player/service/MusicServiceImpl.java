package com.player.service;

import com.player.model.dao.MusicDao;

import com.player.model.entity.MusicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("MusicServiceImpl")
@Transactional(rollbackFor = SQLException.class)
public class MusicServiceImpl implements MusicService {
    private MusicDao musicDao;

    @Autowired
    public void setMusicDao(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicEntity> findAll() {
        List musics = musicDao.findAll();
        List<MusicEntity> list = new ArrayList<>();
        for (Object music : musics) {
            MusicEntity musicEntity = (MusicEntity) music;
            list.add(musicEntity);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicEntity> findByName(String name) {
        List musics = musicDao.findByName(name);
        List<MusicEntity> list = new ArrayList<>();
        for (Object music : musics) {
            MusicEntity musicEntity = (MusicEntity) music;
            list.add(musicEntity);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public MusicEntity findById(int id) {
        return musicDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MusicEntity> findBySinger(int singerId) {
        List musics = musicDao.findBySinger(singerId);
        List<MusicEntity> list = new ArrayList<>();
        for (Object music : musics) {
            MusicEntity musicEntity = (MusicEntity) music;
            list.add(musicEntity);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void save(MusicEntity music) {
        musicDao.save(music);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void update(MusicEntity music) {
        musicDao.update(music);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void delete(MusicEntity music) {
        musicDao.delete(music);
    }
}
