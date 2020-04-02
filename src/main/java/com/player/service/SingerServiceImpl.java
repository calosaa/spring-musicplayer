package com.player.service;

import com.player.model.dao.MusicDaoImpl;
import com.player.model.dao.SingerDao;
import com.player.model.entity.MusicEntity;
import com.player.model.entity.SingerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("SingerServiceImpl")
@Transactional(rollbackFor = SQLException.class)
public class SingerServiceImpl implements SingerService {
    private SingerDao singerDao;
    private MusicDaoImpl musicDao;

    @Autowired
    public void setSingerDao(SingerDao singerDao) {
        this.singerDao = singerDao;
    }

    @Autowired
    public void setMusicDao(MusicDaoImpl musicDao) {
        this.musicDao = musicDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SingerEntity> findAll() {
        List singers = singerDao.findAll();
        List<SingerEntity> list = new ArrayList<>();
        for (Object singer : singers) {
            SingerEntity singerEntity = (SingerEntity) singer;
            list.add(singerEntity);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SingerEntity> findByName(String name) {
        List singers = singerDao.findByName(name);
        List<SingerEntity> list = new ArrayList<>();
        for (Object singer : singers) {
            SingerEntity singerEntity = (SingerEntity) singer;
            list.add(singerEntity);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public SingerEntity findById(int id) {
        return singerDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void save(SingerEntity singer) {
        singerDao.save(singer);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void update(SingerEntity singer) {
        singerDao.update(singer);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void delete(SingerEntity singer) {
        singerDao.delete(singer);
        List musics = musicDao.findBySinger(singer.getId());
        for (Object music : musics) {
            MusicEntity musicEntity = (MusicEntity) music;
            musicDao.delete(musicEntity);
        }
    }
}
